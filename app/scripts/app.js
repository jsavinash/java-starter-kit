/* ==========================================================================
   Grokking System Design — Interactive Educative App Controller
   ========================================================================== */

document.addEventListener('DOMContentLoaded', () => {
  // Global State
  let currentLessonId = 'topic-1';
  let completedLessons = new Set(JSON.parse(localStorage.getItem('gsd_completed_lessons') || '[]'));
  let activeTheme = localStorage.getItem('gsd_theme') || 'light';
  
  // Flattened Lessons for Easy Lookup & Search
  const flattenedLessons = [];
  COURSE_DATA.forEach(mod => {
    mod.sections.forEach(sec => {
      sec.lessons.forEach(les => {
        flattenedLessons.push({
          ...les,
          moduleTitle: mod.title,
          sectionTitle: sec.title
        });
      });
    });
  });

  // Init Feather Icons
  if (window.feather) {
    feather.replace();
  }

  // Set Initial Theme
  document.documentElement.setAttribute('data-theme', activeTheme);
  updateThemeIcon();

  // Render Sidebar
  renderSidebar();

  // Load Initial Lesson
  const urlParams = new URLSearchParams(window.location.search);
  const requestedLesson = urlParams.get('lesson');
  if (requestedLesson && flattenedLessons.some(l => l.id === requestedLesson)) {
    currentLessonId = requestedLesson;
  }
  loadLesson(currentLessonId);

  // Setup Event Listeners
  setupEventListeners();

  // ==========================================================================
  // Markdown Renderer
  // ==========================================================================
  function parseMarkdown(md) {
    if (!md) return '';

    let html = md;

    // Escape HTML special chars inside code blocks (handled during code conversion)

    // Code Blocks: ```lang ... ```
    html = html.replace(/```(\w*)\n([\s\S]*?)```/g, (match, lang, code) => {
      const escapedCode = code
        .replace(/&/g, '&amp;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;');
      
      // Check if code block is a Q&A / Question block
      if (lang === 'Question' || code.includes('Question\n') || code.includes('Answer\n')) {
        return `<div class="quiz-card">
          <div class="quiz-question"><i class="feather" data-feather="help-circle" style="color: var(--brand-primary); margin-right: 0.4rem;"></i> ${escapedCode.split('Answer')[0]}</div>
          <button class="quiz-reveal-btn" onclick="this.nextElementSibling.style.display = this.nextElementSibling.style.display === 'block' ? 'none' : 'block'">Show Answer</button>
          <div class="quiz-answer-content">${escapedCode.split('Answer')[1] || escapedCode}</div>
        </div>`;
      }
      
      return `<pre><code class="language-${lang}">${escapedCode}</code></pre>`;
    });

    // Inline Code `code`
    html = html.replace(/`([^`]+)`/g, '<code>$1</code>');

    // Tables Conversion
    html = html.replace(/^\|(.+)\|\s*$/gm, (match) => {
      return match;
    });

    // Simple Table Parser
    const lines = html.split('\n');
    let inTable = false;
    let tableHtml = '';
    const newLines = [];

    for (let i = 0; i < lines.length; i++) {
      let line = lines[i].trim();
      if (line.startswith?.('|') || line.startsWith('|')) {
        if (!inTable) {
          inTable = true;
          tableHtml = '<table>';
          // Header row
          const cells = line.split('|').filter(c => c.trim() !== '').map(c => `<th>${c.trim()}</th>`).join('');
          tableHtml += `<thead><tr>${cells}</tr></thead><tbody>`;
          // Skip divider row if next line is |---|
          if (lines[i+1] && lines[i+1].includes('---')) {
            i++;
          }
        } else {
          // Data row
          const cells = line.split('|').filter(c => c.trim() !== '').map(c => `<td>${c.trim()}</td>`).join('');
          tableHtml += `<tr>${cells}</tr>`;
        }
      } else {
        if (inTable) {
          inTable = false;
          tableHtml += '</tbody></table>';
          newLines.push(tableHtml);
          tableHtml = '';
        }
        newLines.push(line);
      }
    }
    if (inTable) {
      tableHtml += '</tbody></table>';
      newLines.push(tableHtml);
    }
    html = newLines.join('\n');

    // Headings (H1 - H4)
    html = html.replace(/^#### (.*$)/gim, (m, g1) => `<h4 id="${slugify(g1)}">${g1}</h4>`);
    html = html.replace(/^### (.*$)/gim, (m, g1) => `<h3 id="${slugify(g1)}">${g1}</h3>`);
    html = html.replace(/^## (.*$)/gim, (m, g1) => `<h2 id="${slugify(g1)}">${g1}</h2>`);
    html = html.replace(/^# (.*$)/gim, (m, g1) => `<h1 id="${slugify(g1)}">${g1}</h1>`);

    // Callouts / Blockquotes
    html = html.replace(/^>\s*\[!(NOTE|TIP|WARNING|IMPORTANT|CAUTION)\]\s*(.*$)/gim, (m, type, content) => {
      return `<div class="callout callout-${type.toLowerCase()}"><strong>${type}:</strong> ${content}</div>`;
    });
    html = html.replace(/^>\s*(.*$)/gim, '<div class="callout callout-note">$1</div>');

    // Images
    html = html.replace(/!\[(.*?)\]\((.*?)\)/g, '<img src="$2" alt="$1" class="lesson-img-zoomable" />');

    // Links
    html = html.replace(/\[(.*?)\]\((.*?)\)/g, '<a href="$2" target="_blank" rel="noopener">$1</a>');

    // Bold / Italic
    html = html.replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>');
    html = html.replace(/\*(.*?)\*/g, '<em>$1</em>');

    // Unordered lists
    html = html.replace(/^\s*[-*+]\s+(.*$)/gim, '<li>$1</li>');
    html = html.replace(/(<li>.*<\/li>)/g, '<ul>$1</ul>');
    html = html.replace(/<\/ul>\s*<ul>/g, '');

    // Paragraphs
    const paras = html.split('\n\n');
    html = paras.map(p => {
      p = p.trim();
      if (p.startsWith('<h') || p.startsWith('<pre') || p.startsWith('<table') || p.startsWith('<div') || p.startsWith('<ul') || p.startsWith('<ol')) {
        return p;
      }
      return `<p>${p}</p>`;
    }).join('\n');

    return html;
  }

  function slugify(text) {
    return text.toString().toLowerCase().trim()
      .replace(/\s+/g, '-')
      .replace(/[^\w\-]+/g, '')
      .replace(/\-\-+/g, '-');
  }

  // ==========================================================================
  // Sidebar Renderer
  // ==========================================================================
  function renderSidebar(filterText = '') {
    const sidebarNav = document.getElementById('sidebarNav');
    sidebarNav.innerHTML = '';

    COURSE_DATA.forEach((mod, modIdx) => {
      const groupEl = document.createElement('div');
      groupEl.className = `module-group ${modIdx === 0 ? 'open' : ''}`;

      const headerEl = document.createElement('div');
      headerEl.className = 'module-header';
      headerEl.innerHTML = `
        <div class="module-title-wrap">
          <i data-feather="${mod.icon || 'book-open'}" class="module-icon" style="width: 16px; height: 16px;"></i>
          <span>${mod.title}</span>
        </div>
        <i data-feather="chevron-right" class="module-chevron" style="width: 16px; height: 16px;"></i>
      `;

      headerEl.addEventListener('click', () => {
        groupEl.classList.toggle('open');
      });

      const lessonListEl = document.createElement('ul');
      lessonListEl.className = 'lesson-list';

      let hasMatchingLesson = false;

      mod.sections.forEach(sec => {
        sec.lessons.forEach(les => {
          if (filterText && !les.title.toLowerCase().includes(filterText.toLowerCase())) {
            return;
          }
          hasMatchingLesson = true;

          const isCompleted = completedLessons.has(les.id);
          const isActive = les.id === currentLessonId;

          const lesLi = document.createElement('li');
          lesLi.className = `lesson-item ${isActive ? 'active' : ''} ${isCompleted ? 'completed' : ''}`;
          lesLi.dataset.id = les.id;

          lesLi.innerHTML = `
            <div style="display: flex; align-items: center;">
              <span class="lesson-status">${isCompleted ? '✓' : ''}</span>
              <span>${les.title}</span>
            </div>
            <span class="lesson-time">${les.readingTime || '10 min'}</span>
          `;

          lesLi.addEventListener('click', (e) => {
            e.stopPropagation();
            loadLesson(les.id);
          });

          lessonListEl.appendChild(lesLi);
        });
      });

      if (!filterText || hasMatchingLesson) {
        if (filterText) groupEl.classList.add('open');
        groupEl.appendChild(headerEl);
        groupEl.appendChild(lessonListEl);
        sidebarNav.appendChild(groupEl);
      }
    });

    if (window.feather) feather.replace();
    updateProgressBadges();
  }

  // ==========================================================================
  // Lesson Loader
  // ==========================================================================
  function loadLesson(lessonId) {
    const lesson = flattenedLessons.find(l => l.id === lessonId);
    if (!lesson) return;

    currentLessonId = lessonId;

    // Update URL hash/query without reload
    const newUrl = `${window.location.pathname}?lesson=${lessonId}`;
    window.history.pushState({ lessonId }, '', newUrl);

    // Update Header Meta
    document.getElementById('lessonTitle').textContent = lesson.title;
    document.getElementById('breadcrumbCurrent').textContent = lesson.title;
    document.getElementById('lessonTimeChip').innerHTML = `<i data-feather="clock" style="width: 14px; height: 14px;"></i> ${lesson.readingTime || '12 min read'}`;
    document.getElementById('lessonCategoryChip').innerHTML = `<i data-feather="folder" style="width: 14px; height: 14px;"></i> ${lesson.folder}`;

    // Update Complete Button
    const completeBtn = document.getElementById('markCompleteBtn');
    const isCompleted = completedLessons.has(lessonId);
    if (isCompleted) {
      completeBtn.classList.add('is-completed');
      document.getElementById('markCompleteText').textContent = 'Completed ✓';
    } else {
      completeBtn.classList.remove('is-completed');
      document.getElementById('markCompleteText').textContent = 'Mark as Completed';
    }

    // Render Body
    const lessonBody = document.getElementById('lessonBody');
    lessonBody.innerHTML = parseMarkdown(lesson.content);

    // Show Back-of-the-envelope estimator if lesson relates to calculations or Youtube/Twitter designs
    const calcSection = document.getElementById('calculatorSection');
    if (lesson.folder.includes('Back-of-the-envelope') || lesson.title.includes('Requirements') || lesson.title.includes('YouTube') || lesson.title.includes('Twitter')) {
      calcSection.style.display = 'block';
    } else {
      calcSection.style.display = 'none';
    }

    // Render Right TOC ("On this page")
    buildRightToc(lessonBody);

    // Image Zoom Lightbox handler
    document.querySelectorAll('.lesson-img-zoomable').forEach(img => {
      img.addEventListener('click', () => {
        document.getElementById('lightboxImg').src = img.src;
        document.getElementById('imageModal').classList.add('active');
      });
    });

    // Update Active Class in Sidebar
    document.querySelectorAll('.lesson-item').forEach(el => {
      if (el.dataset.id === lessonId) {
        el.classList.add('active');
        // Scroll into view if needed
        el.scrollIntoView({ block: 'nearest', behavior: 'smooth' });
      } else {
        el.classList.remove('active');
      }
    });

    // Next / Prev Buttons
    const currentIdx = flattenedLessons.findIndex(l => l.id === lessonId);
    const prevLesson = flattenedLessons[currentIdx - 1];
    const nextLesson = flattenedLessons[currentIdx + 1];

    const prevBtn = document.getElementById('prevLessonBtn');
    if (prevLesson) {
      prevBtn.style.visibility = 'visible';
      document.getElementById('prevLessonTitle').textContent = prevLesson.title;
      prevBtn.onclick = () => loadLesson(prevLesson.id);
    } else {
      prevBtn.style.visibility = 'hidden';
    }

    const nextBtn = document.getElementById('nextLessonBtn');
    if (nextLesson) {
      nextBtn.style.visibility = 'visible';
      document.getElementById('nextLessonTitle').textContent = nextLesson.title;
      nextBtn.onclick = () => loadLesson(nextLesson.id);
    } else {
      nextBtn.style.visibility = 'hidden';
    }

    // Reset Scroll
    window.scrollTo({ top: 0, behavior: 'smooth' });

    if (window.feather) feather.replace();
  }

  // ==========================================================================
  // Right In-Page Outline TOC ("On this page")
  // ==========================================================================
  function buildRightToc(containerEl) {
    const rightTocList = document.getElementById('rightTocList');
    rightTocList.innerHTML = '';

    const headings = containerEl.querySelectorAll('h1, h2, h3');
    headings.forEach(h => {
      if (!h.id) h.id = slugify(h.textContent);

      const li = document.createElement('li');
      const link = document.createElement('a');
      link.className = 'right-toc-link';
      link.href = `#${h.id}`;
      link.textContent = h.textContent;
      if (h.tagName === 'H3') {
        link.style.paddingLeft = '1.5rem';
        link.style.fontSize = '0.8rem';
      }

      link.addEventListener('click', (e) => {
        e.preventDefault();
        document.getElementById(h.id)?.scrollIntoView({ behavior: 'smooth' });
      });

      li.appendChild(link);
      rightTocList.appendChild(li);
    });
  }

  // ==========================================================================
  // Back-of-the-envelope Calculator Logic
  // ==========================================================================
  function computeCalculator() {
    const dau = parseFloat(document.getElementById('calcDau').value) || 0;
    const actions = parseFloat(document.getElementById('calcActions').value) || 0;
    const payloadKb = parseFloat(document.getElementById('calcPayload').value) || 0;
    const serverRps = parseFloat(document.getElementById('calcServerRps').value) || 8000;

    // Total Requests per Day
    const totalRequestsDay = dau * actions;
    // Avg Requests per Second
    const rps = Math.round(totalRequestsDay / 86400);

    // Storage Needed per Day (in GB / TB)
    const storageBytesDay = totalRequestsDay * (payloadKb * 1024);
    const storageTbDay = (storageBytesDay / (1024 * 1024 * 1024 * 1024)).toFixed(2);

    // Bandwidth in Gbps
    const bytesPerSec = rps * (payloadKb * 1024);
    const gbps = ((bytesPerSec * 8) / 1e9).toFixed(2);

    // Server Count
    const servers = Math.ceil(rps / serverRps);

    document.getElementById('resRps').textContent = rps.toLocaleString();
    document.getElementById('resStorage').textContent = `${storageTbDay} TB / day`;
    document.getElementById('resBandwidth').textContent = `${gbps} Gbps`;
    document.getElementById('resServers').textContent = `${servers} Servers`;
  }

  ['calcDau', 'calcActions', 'calcPayload', 'calcServerRps'].forEach(id => {
    document.getElementById(id)?.addEventListener('input', computeCalculator);
  });
  computeCalculator();

  // ==========================================================================
  // Progress & Badges
  // ==========================================================================
  function updateProgressBadges() {
    const total = flattenedLessons.length;
    const completedCount = completedLessons.size;
    const percent = total > 0 ? Math.round((completedCount / total) * 100) : 0;

    document.getElementById('completedRatioBadge').textContent = `${completedCount} / ${total} Done`;
    document.getElementById('progressPercentText').textContent = `${percent}%`;
    document.getElementById('globalProgressFill').style.width = `${percent}%`;
  }

  // ==========================================================================
  // Search Modal & Keyboard Shortcuts
  // ==========================================================================
  const searchModal = document.getElementById('searchModal');
  const modalSearchInput = document.getElementById('modalSearchInput');
  const modalSearchResults = document.getElementById('modalSearchResults');

  function openSearchModal() {
    searchModal.classList.add('active');
    modalSearchInput.value = '';
    modalSearchInput.focus();
    renderSearchResults('');
  }

  function closeSearchModal() {
    searchModal.classList.remove('active');
  }

  function renderSearchResults(query) {
    modalSearchResults.innerHTML = '';
    const q = query.toLowerCase().trim();

    const matches = flattenedLessons.filter(l => 
      !q || l.title.toLowerCase().includes(q) || l.folder.toLowerCase().includes(q) || l.content.toLowerCase().includes(q)
    ).slice(0, 15);

    if (matches.length === 0) {
      modalSearchResults.innerHTML = `<div style="padding: 1rem; text-align: center; color: var(--text-muted);">No matching lessons found for "${query}"</div>`;
      return;
    }

    matches.forEach(m => {
      const item = document.createElement('div');
      item.className = 'search-result-item';
      item.innerHTML = `
        <div class="search-res-title">${m.title}</div>
        <div class="search-res-path">${m.moduleTitle} &rsaquo; ${m.folder} • ${m.readingTime}</div>
      `;
      item.addEventListener('click', () => {
        closeSearchModal();
        loadLesson(m.id);
      });
      modalSearchResults.appendChild(item);
    });
  }

  // ==========================================================================
  // Event Listeners
  // ==========================================================================
  function setupEventListeners() {
    // Theme Toggle
    document.getElementById('themeToggleBtn').addEventListener('click', () => {
      activeTheme = activeTheme === 'light' ? 'dark' : 'light';
      document.documentElement.setAttribute('data-theme', activeTheme);
      localStorage.setItem('gsd_theme', activeTheme);
      updateThemeIcon();
    });

    // Mark as Completed
    document.getElementById('markCompleteBtn').addEventListener('click', () => {
      if (completedLessons.has(currentLessonId)) {
        completedLessons.delete(currentLessonId);
      } else {
        completedLessons.add(currentLessonId);
      }
      localStorage.setItem('gsd_completed_lessons', JSON.stringify([...completedLessons]));
      
      loadLesson(currentLessonId);
      renderSidebar();
    });

    // Search Trigger
    document.getElementById('openSearchBtn').addEventListener('click', openSearchModal);
    searchModal.addEventListener('click', (e) => {
      if (e.target === searchModal) closeSearchModal();
    });

    modalSearchInput.addEventListener('input', (e) => {
      renderSearchResults(e.target.value);
    });

    // Hotkey Cmd+K or Ctrl+K
    document.addEventListener('keydown', (e) => {
      if ((e.metaKey || e.ctrlKey) && e.key === 'k') {
        e.preventDefault();
        openSearchModal();
      }
      if (e.key === 'Escape') {
        closeSearchModal();
        document.getElementById('imageModal').classList.remove('active');
      }
    });

    // Sidebar Live Filter
    document.getElementById('sidebarSearchInput').addEventListener('input', (e) => {
      renderSidebar(e.target.value);
    });

    // Estimator Button Scroll
    document.getElementById('openCalcBtn').addEventListener('click', () => {
      const calcSection = document.getElementById('calculatorSection');
      calcSection.style.display = 'block';
      calcSection.scrollIntoView({ behavior: 'smooth' });
    });

    // Close Lightbox
    document.getElementById('closeLightboxBtn').addEventListener('click', () => {
      document.getElementById('imageModal').classList.remove('active');
    });
    document.getElementById('imageModal').addEventListener('click', (e) => {
      if (e.target === document.getElementById('imageModal')) {
        document.getElementById('imageModal').classList.remove('active');
      }
    });
  }

  function updateThemeIcon() {
    const icon = document.getElementById('themeIcon');
    if (icon) {
      icon.setAttribute('data-feather', activeTheme === 'light' ? 'moon' : 'sun');
      if (window.feather) feather.replace();
    }
  }
});
