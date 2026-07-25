/*
 * This project is licensed under the MIT license. Module model-view-viewmodel is using ZK framework licensed under LGPL (see lgpl-3.0.txt).
 *
 * The MIT License
 * Copyright © 2014-2022 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.async.method.invocation;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;

/** ThreadAsyncExecutorTest */
class ThreadAsyncExecutorTest {

  /**
   * Test used to verify the happy path of {@link ThreadAsyncExecutor#startProcess(Callable)}
   */
  @Test
  void testSuccessfulTaskWithoutCallback() {
    assertTimeout(
        ofMillis(3000),
        () -> {
          final var executor = new ThreadAsyncExecutor();
          final var result = new Object();

          final var asyncResult = executor.startProcess(() -> result);
          assertNotNull(asyncResult);
          asyncResult.await();
          assertTrue(asyncResult.isCompleted());
          assertSame(result, asyncResult.getValue());
        });
  }

  /**
   * Test used to verify the happy path of {@link ThreadAsyncExecutor#startProcess(Callable, AsyncCallback)}
   */
  @Test
  void testSuccessfulTaskWithCallback() {
    assertTimeout(
        ofMillis(3000),
        () -> {
          final var executor = new ThreadAsyncExecutor();
          final var result = new Object();
          final var callbackInvoked = new AtomicBoolean(false);

          final var asyncResult = executor.startProcess(
              () -> result,
              new AsyncCallback<>() {
                @Override
                public void onComplete(Object value) {
                  callbackInvoked.set(true);
                }

                @Override
                public void onError(Exception ex) {
                  fail("Unexpected error: " + ex.getMessage());
                }
              });
          assertNotNull(asyncResult);
          asyncResult.await();
          assertTrue(asyncResult.isCompleted());
          assertTrue(callbackInvoked.get());
          assertSame(result, asyncResult.getValue());
        });
  }

  /**
   * Test used to verify the happy path of {@link ThreadAsyncExecutor#startProcess(Callable)}
   * when a task takes a while to execute
   */
  @Test
  void testLongRunningTaskWithoutCallback() {
    assertTimeout(
        ofMillis(5000),
        () -> {
          final var executor = new ThreadAsyncExecutor();
          final var result = new Object();

          final var asyncResult = executor.startProcess(() -> {
            Thread.sleep(1500);
            return result;
          });
          assertNotNull(asyncResult);
          assertFalse(asyncResult.isCompleted());

          assertThrows(IllegalStateException.class, asyncResult::getValue);

          asyncResult.await();
          assertTrue(asyncResult.isCompleted());
          assertSame(result, asyncResult.getValue());
        });
  }

  /**
   * Test used to verify the happy path of {@link ThreadAsyncExecutor#startProcess(Callable,
   * AsyncCallback)} when a task takes a while to execute
   */
  @Test
  void testLongRunningTaskWithCallback() {
    assertTimeout(
        ofMillis(5000),
        () -> {
          final var executor = new ThreadAsyncExecutor();
          final var result = new Object();
          final var callbackInvoked = new AtomicBoolean(false);

          final var asyncResult = executor.startProcess(
              () -> {
                Thread.sleep(1500);
                return result;
              },
              new AsyncCallback<>() {
                @Override
                public void onComplete(Object value) {
                  callbackInvoked.set(true);
                }

                @Override
                public void onError(Exception ex) {
                  fail("Unexpected error: " + ex.getMessage());
                }
              });
          assertNotNull(asyncResult);
          assertFalse(asyncResult.isCompleted());

          assertThrows(IllegalStateException.class, asyncResult::getValue);

          asyncResult.await();
          assertTrue(asyncResult.isCompleted());
          assertTrue(callbackInvoked.get());
          assertSame(result, asyncResult.getValue());
        });
  }

  /**
   * Test used to verify the happy path of {@link ThreadAsyncExecutor#startProcess(Callable)}
   * while waiting on the result using {@link ThreadAsyncExecutor#endProcess(AsyncResult)}
   */
  @Test
  void testEndProcess() {
    assertTimeout(
        ofMillis(5000),
        () -> {
          final var executor = new ThreadAsyncExecutor();
          final var result = new Object();

          final var asyncResult = executor.startProcess(() -> {
            Thread.sleep(1500);
            return result;
          });
          assertNotNull(asyncResult);
          assertFalse(asyncResult.isCompleted());

          assertThrows(IllegalStateException.class, asyncResult::getValue);

          assertSame(result, executor.endProcess(asyncResult));
          assertTrue(asyncResult.isCompleted());

          assertSame(result, executor.endProcess(asyncResult));
        });
  }

  /**
   * Test used to verify the behaviour of {@link ThreadAsyncExecutor#startProcess(Callable)}
   * when the callable is 'null'
   */
  @Test
  void testNullTask() {
    assertTimeout(
        ofMillis(3000),
        () -> {
          final var executor = new ThreadAsyncExecutor();
          final var asyncResult = executor.startProcess(null);

          assertNotNull(asyncResult);
          asyncResult.await();
          assertTrue(asyncResult.isCompleted());

          var exception = assertThrows(ExecutionException.class, asyncResult::getValue);
          assertNotNull(exception.getMessage());
          assertNotNull(exception.getCause());
          assertEquals(NullPointerException.class, exception.getCause().getClass());
        });
  }

  /**
   * Test used to verify the behaviour of {@link ThreadAsyncExecutor#startProcess(Callable,
   * AsyncCallback)} when the callable is 'null', but the asynchronous callback is provided
   */
  @Test
  void testNullTaskWithCallback() {
    assertTimeout(
        ofMillis(3000),
        () -> {
          final var executor = new ThreadAsyncExecutor();
          final var errorRef = new AtomicReference<Exception>();

          final var asyncResult = executor.startProcess(
              null,
              new AsyncCallback<>() {
                @Override
                public void onComplete(Object value) {
                  fail("onComplete should not be called");
                }

                @Override
                public void onError(Exception ex) {
                  errorRef.set(ex);
                }
              });

          assertNotNull(asyncResult);
          asyncResult.await();
          assertTrue(asyncResult.isCompleted());

          var exception = errorRef.get();
          assertNotNull(exception);
          assertEquals(NullPointerException.class, exception.getClass());

          var execException = assertThrows(ExecutionException.class, asyncResult::getValue);
          assertNotNull(execException.getMessage());
          assertNotNull(execException.getCause());
          assertEquals(NullPointerException.class, execException.getCause().getClass());
        });
  }

  /**
   * Test used to verify the behaviour of {@link ThreadAsyncExecutor#startProcess(Callable,
   * AsyncCallback)} when both the callable and the asynchronous callback are 'null'
   */
  @Test
  void testNullTaskWithNullCallback() {
    assertTimeout(
        ofMillis(3000),
        () -> {
          final var executor = new ThreadAsyncExecutor();
          final var asyncResult = executor.startProcess(null, null);

          assertNotNull(asyncResult);
          asyncResult.await();
          assertTrue(asyncResult.isCompleted());

          var exception = assertThrows(ExecutionException.class, asyncResult::getValue);
          assertNotNull(exception.getMessage());
          assertNotNull(exception.getCause());
          assertEquals(NullPointerException.class, exception.getCause().getClass());
        });
  }
}