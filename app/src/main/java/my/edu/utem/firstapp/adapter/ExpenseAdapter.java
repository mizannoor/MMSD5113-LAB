package my.edu.utem.firstapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import my.edu.utem.firstapp.R;
import my.edu.utem.firstapp.holder.ExpenseViewHolder;
import my.edu.utem.firstapp.model.Expense;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseViewHolder> {
    private final LayoutInflater layoutInflater;
    private final List<Expense> expenseList;

    public ExpenseAdapter(LayoutInflater layoutInflater, List<Expense> expenseList) {
        this.layoutInflater = layoutInflater;
        this.expenseList = expenseList;
    }

    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_expenses, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        holder.setExpense(expenseList.get(position));
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }
}
