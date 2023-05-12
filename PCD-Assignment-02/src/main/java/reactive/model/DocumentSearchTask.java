package reactive.model;

import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.ObservableEmitter;
import reactive.utils.Pair;

import java.util.concurrent.RecursiveTask;

public class DocumentSearchTask {
    
	private final Document document;
    private ObservableEmitter<Pair<String, Integer>> emitter;

    public DocumentSearchTask(Document document, ObservableEmitter<Pair<String, Integer>> emitter) {
        super();
        this.document = document;
        this.emitter = emitter;
        this.exec();
    }
    private void exec(){
        emitter.onNext(new Pair<>(document.getPath(), document.countNumLines()));
    }

}

