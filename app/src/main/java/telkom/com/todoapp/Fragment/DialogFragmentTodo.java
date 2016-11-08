package telkom.com.todoapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import telkom.com.todoapp.R;

/**
 * Created by sukri on 08/11/16.
 */

public class DialogFragmentTodo extends DialogFragment {

    private EditText title,deskripsi;
    private TextInputEditText ttitle,tdeskripsi;
    private CalendarView cv;
    private Spinner level;

    public DialogFragmentTodo() {
    }

    public static DialogFragmentTodo newInstance(String title) {
        DialogFragmentTodo frag = new DialogFragmentTodo();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_todo, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title = (EditText) view.findViewById(R.id.Title);
        deskripsi = (EditText) view.findViewById(R.id.Deskripsi);
        ttitle = (TextInputEditText) view.findViewById(R.id.tilTitle);
        tdeskripsi = (TextInputEditText) view.findViewById(R.id.tilDeskripsi);
        cv = (CalendarView) view.findViewById(R.id.calendarView);
        level = (Spinner) view.findViewById(R.id.spinner);

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}
