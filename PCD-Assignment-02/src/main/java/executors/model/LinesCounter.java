/*
 * Fork-Join example, adapted from
 * http://www.oracle.com/technetwork/articles/java/fork-join-422606.html
 * 
 */
package executors.model;

import executors.utils.SynchronizedList;

import java.util.concurrent.ForkJoinPool;

public class LinesCounter {

    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public SynchronizedList countOccurrencesInParallel(Folder folder) {
        return forkJoinPool.invoke(new FolderSearchTask(folder));
    }

}
