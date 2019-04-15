package com.example.inclass07;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class AddExpenseFragment extends Fragment {
    Button buttonAddExpense, buttonCancel;
    private TextView textviewExpenseName, textViewAmount, textViewCategory;
    Expense expense = new Expense();
    private AddExpenseFragmentInterface mListener;
    private ArrayList<Expense> expenseNew;
    static  String ARG_PARAM="ItemList";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddExpenseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddExpenseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddExpenseFragment newInstance(String param1, String param2) {
        AddExpenseFragment fragment = new AddExpenseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Fragment setData(ArrayList<Expense> expense) {
        AddExpenseFragment fragment = new AddExpenseFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, expense);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*buttonAddExpense = getActivity().findViewById(R.id.addExpenseButton);
        buttonCancel = getActivity().findViewById(R.id.cancelButton);
        textviewExpenseName = getActivity().findViewById(R.id.nameeditText);
        textViewAmount = getActivity().findViewById(R.id.amounteditText);
        textViewCategory = getActivity().findViewById(R.id.textViewExpenseCategory);

        buttonAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("demo","AddExpense clicked");
                String ExpenseName = textviewExpenseName.getText().toString();
                String Amount = textViewAmount.getText().toString();
                //String Category = textViewCategory.getText().toString();

                Expense expense= new Expense(ExpenseName,Amount,"Category");
                expense.setExpenseName(ExpenseName);
                expense.setExpenseAmount(Amount);
                expense.setExpenseCategory("Category");

                Log.d("demo","data in expense after add expense clicked"+expense.toString());



                mListener.AddButtonClicked(expense);


                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.layout_container, new ExpenseAppFragment(), "ExpenseAppFragment")
                        .addToBackStack(null)
                        .commit();


            }
        });*/
        Spinner spinner = getActivity().findViewById(R.id.spinnerCategory);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.categories_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        getActivity().findViewById(R.id.addExpenseButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText expenseName = getActivity().findViewById(R.id.nameeditText);
                EditText expenseAmount = getActivity().findViewById(R.id.amounteditText);
                Spinner category = getActivity().findViewById(R.id.spinnerCategory);
                if(expenseName.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getActivity(), "Please Enter Expense Name", Toast.LENGTH_SHORT).show();
                }else if(expenseAmount.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getActivity(), "Please Enter Expense Amount", Toast.LENGTH_SHORT).show();
                }else if(category.getSelectedItem().toString().equalsIgnoreCase("Select Category")){
                    Toast.makeText(getActivity(), "Please Select Expense Category", Toast.LENGTH_SHORT).show();
                }else {
                    expense.setExpenseName(expenseName.getText().toString());
                    expense.setAmount(expenseAmount.getText().toString());
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                    expense.setDate(sdf.format(cal.getTime()).toString());
                    expense.setCategory(category.getSelectedItem().toString());
                    Log.d("demo", "Expense " + expense.toString());
                    mListener.AddButtonClicked(expense);
                }
            }
        });
        getActivity().findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.layout_container, ExpenseAppFragment.setData(expenseNew), "CancelButtonClick").commit();
                //getFragmentManager().popBackStackImmediate();
            }
        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onStart() {
    super.onStart();
    getActivity().setTitle("Add Expense");
    if (getArguments() != null) {
        expenseNew = (ArrayList<Expense>) getArguments().getSerializable(ARG_PARAM);
    }
}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_expense, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddExpenseFragmentInterface) {
            mListener = (AddExpenseFragmentInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface AddExpenseFragmentInterface {
        // TODO: Update argument type and name
        void AddButtonClicked(Expense expense);
    }
}
