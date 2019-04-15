package com.example.inclass07;

import android.app.Fragment;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExpenseAppFragment.ExpenseAppFragmentInterface, AddExpenseFragment.AddExpenseFragmentInterface
{
    /*Expense expenseinMain;*/
    final ArrayList<Expense> expenseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Expense App");



        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layout_container, new ExpenseAppFragment() ,"ExpenseAppFragment")
                .commit();
    }

    @Override
    public void gotoAddExpenseFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, AddExpenseFragment.setData(expenseList), "AddExpenses").commit();
    }



   @Override
    public void AddButtonClicked(Expense expense) {
       expenseList.add(expense);
       Log.d("demo", "test1 :" + expense.toString());
       getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, ExpenseAppFragment.setData(expenseList), "AddExpenses").commit();
    }


  /*  @Override
    public void showExpenseFragment(Expense expense) {
        Log.d("demo","in showExpenseFragment"+expense);
    }*/
}
