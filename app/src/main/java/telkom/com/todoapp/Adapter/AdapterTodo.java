package telkom.com.todoapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;
import telkom.com.todoapp.Data.Todo;
import telkom.com.todoapp.Prefs;
import telkom.com.todoapp.R;
import telkom.com.todoapp.RealmController;

/**
 * Created by sukri on 08/11/16.
 */

public class AdapterTodo extends RealmRecyclerViewAdapter<Todo> {

    final Context context;
    private Realm realm;
    private LayoutInflater inflater;

    public AdapterTodo(Context context) {

        this.context = context;
    }

    // create new views (invoked by the layout manager)
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate a new card view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_todo, parent, false);
        return new CardViewHolder(view);
    }

    // replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        realm = RealmController.getInstance().getRealm();

        // get the article
        final Todo book = getItem(position);
        // cast the generic view holder to our specific one
        final CardViewHolder holder = (CardViewHolder) viewHolder;

        // set the title and the snippet
        holder.textTitle.setText(book.getTitle());
        holder.textLevel.setText(book.getLevel());
        holder.textdate.setText(book.getTanggal());


        //remove single match from realm
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                RealmResults<Todo> results = realm.where(Todo.class).findAll();

                // Get the book title to show it in toast message
                Todo b = results.get(position);
                String title = b.getTitle();

                // All changes to data must happen in a transaction
                realm.beginTransaction();

                // remove single match
                results.remove(position);
                realm.commitTransaction();

                if (results.size() == 0) {
                    Prefs.with(context).setPreLoad(false);
                }

                notifyDataSetChanged();

                Toast.makeText(context, title + " is removed from Realm", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //update single match from realm
        holder.card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            }
        });
    }

    // return the size of your data set (invoked by the layout manager)
    public int getItemCount() {

        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout card;
        public TextView textTitle;
        public TextView textdate;
        public TextView textLevel;

        public CardViewHolder(View itemView) {
            // standard view holder pattern with Butterknife view injection
            super(itemView);

            card = (RelativeLayout) itemView.findViewById(R.id.layoutt);
            textTitle = (TextView) itemView.findViewById(R.id.judul);
            textdate = (TextView) itemView.findViewById(R.id.tanggal);
            textLevel = (TextView) itemView.findViewById(R.id.level);
        }
    }
}