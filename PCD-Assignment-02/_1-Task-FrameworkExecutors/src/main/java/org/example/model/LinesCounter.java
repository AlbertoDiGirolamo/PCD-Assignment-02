/*
 * Fork-Join example, adapted from
 * http://www.oracle.com/technetwork/articles/java/fork-join-422606.html
 * 
 */
package org.example.model;

import java.util.concurrent.ForkJoinPool;

public class LinesCounter {

    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public Long countOccurrencesInParallel(Folder folder, String searchedWord) {
        return forkJoinPool.invoke(new FolderSearchTask(this, folder, searchedWord));
    }

}
