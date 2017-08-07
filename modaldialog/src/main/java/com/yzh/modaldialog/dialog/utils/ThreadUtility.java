package com.yzh.modaldialog.dialog.utils;

import android.os.Handler;
import android.os.Looper;

public class ThreadUtility {
    /**
     * the handler of main looper
     */
    private static Handler sHandler = new Handler(Looper.getMainLooper());


    /**
     * run specified runnable and blocked current thread util finished
     *
     * @param runnable
     */
    public static void runOnUiThreadBlocked(Runnable runnable) {
        class SyncRun implements Runnable {
            protected Runnable mRunnable = null;

            public SyncRun(Runnable runnable) {
                mRunnable = runnable;
            }

            public synchronized void run() {
                try {
                    mRunnable.run();
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
                finally {
                    this.notifyAll();
                }
            }
        }

        SyncRun run = new SyncRun(runnable);
        synchronized (run) {
            if (isMain()) {
                runnable.run();
                return;
            }

            sHandler.post(run);

            try {
                run.wait();
            }
            catch (java.lang.InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * check current is main thread or not
     *
     * @return
     */
    public static boolean isMain() {
        return Looper.getMainLooper()
                .getThread() == Thread.currentThread();
    }
}
