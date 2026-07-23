# 🎨 Grokking System Design — Web Application

This folder contains the front-end interactive web application for **High-Level System Design**, styled after Educative.io.

## 🚀 Features

- **Consolidated Master Pages**: 38 unified single-page topic guides combining Requirements, High-Level Architecture, Detailed Design, Trade-offs, and Quizzes into one view.
- **766 Diagram Images**: Integrated zoomable lightbox viewers for all 766 architectural system design diagrams.
- **Back-of-the-Envelope Estimator Widget**: Interactive live calculator computing RPS, storage (TB/day), bandwidth (Gbps), and server counts.
- **Instant Search (`⌘ K`)**: Fuzzy search across all 38 master topics and content snippets.
- **Dark / Light Mode**: Seamless theme switcher with persistent state in `localStorage`.
- **Progress Tracking**: Sidebar completion checkmarks (`✓`) and progress bar recalculations.

## 📁 File Breakdown

- `index.html`: Main HTML5 application shell & layout.
- `styles/main.css`: Educative design system with CSS custom properties (variables for themes, responsive sidebar, callout alert cards, code highlighting).
- `scripts/course-data.js`: Consolidated dataset containing all 38 topic guides and image resolution paths.
- `scripts/app.js`: Main JavaScript controller, markdown renderer, estimator engine, modal manager, and sidebar navigation.

## 🏃 Running the App

```bash
# Start server from parent directory
cd ..
python3 -m http.server 8080
```

Open: **`http://localhost:8080/app/index.html`**
