package telkom.com.todoapp.Adapter;

import android.content.Context;

import io.realm.RealmResults;
import telkom.com.todoapp.Data.Todo;

/**
 * Created by sukri on 08/11/16.
 */

public class RealmTodosAdapter extends RealmModelAdapter<Todo> {

    public RealmTodosAdapter(Context context, RealmResults<Todo> realmResults, boolean automaticUpdate) {

        super(context, realmResults, automaticUpdate);
    }
}