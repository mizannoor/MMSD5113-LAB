package my.edu.utem.firstapp.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import my.edu.utem.firstapp.R;
import my.edu.utem.firstapp.model.Expense;

public class ExpenseViewHolder extends RecyclerView.ViewHolder {
    private TextView txtExpenseName, txtExpenseDate, txtExpensePrice, txtExpenseQty;

    public ExpenseViewHolder(@NonNull View itemView) {
        super(itemView);
        txtExpenseName = itemView.findViewById(R.id.itemExpenseName);
        txtExpenseDate = itemView.findViewById(R.id.itemExpenseDate);
        txtExpensePrice = itemView.findViewById(R.id.itemExpensePrice);
        txtExpenseQty = itemView.findViewById(R.id.itemExpenseQty);
    }

    public void setExpense(Expense expense) {
        txtExpenseName.setText(expense.getExpName());
        txtExpenseDate.setText(expense.getExpDate());
        txtExpensePrice.setText(String.valueOf(expense.getExpPrise()));
        txtExpenseQty.setText(String.valueOf(expense.getExpQty()));
    }
}
