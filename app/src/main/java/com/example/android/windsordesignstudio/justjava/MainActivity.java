package com.example.android.windsordesignstudio.justjava;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

    private TextView quantityTextView;
    private TextView orderTotalTextView;
    private Button orderButton;
    private Button increaseQuantityButton;
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_TOTAL = "total";
    Integer mQuantity;
    Integer mTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quantityTextView = (TextView) findViewById(R.id.quantity);
        orderTotalTextView = (TextView) findViewById(R.id.order_total);

        if (savedInstanceState != null) {
            mQuantity = savedInstanceState.getInt(KEY_QUANTITY, 0);
            mTotal = savedInstanceState.getInt(KEY_TOTAL, 0);
            setTextViews(mQuantity, mTotal);
        } else {
            mQuantity = Integer.parseInt(quantityTextView.getText().toString());
            mTotal = Integer.parseInt(orderTotalTextView.getText().toString());
        }

        orderButton            = (Button) findViewById(R.id.order_button);
        orderButton.setOnClickListener(this);

        increaseQuantityButton = (Button) findViewById(R.id.increase_quantity_button);
        increaseQuantityButton.setOnClickListener(this);

        Button decreaseQuantityButton = (Button) findViewById(R.id.decrease_quantity_button);
        decreaseQuantityButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == orderButton) {
            // Place order
            // Probably launch other activity?
        } else if (v == increaseQuantityButton) {
            mQuantity = mQuantity + 1;
            mTotal = mTotal + 4;
            setTextViews(mQuantity, mTotal);
        } else {
            if(mQuantity > 0) {
                mQuantity = mQuantity - 1;
                if (mQuantity == 0) {
                    mTotal = mTotal - 4;
                    mQuantity = 0;
                    setTextViews(mQuantity, mTotal);
                } else {
                    mTotal = mTotal - 4;
                    setTextViews(mQuantity, mTotal);
                }
            } else {
                mQuantity = 0;
                setTextViews(mQuantity, mTotal);
                // maybe show a toast?
            }
        }
    }

    public void setTextViews(Integer quantity, Integer total) {
        quantityTextView.setText(mQuantity.toString());
        if (quantity == 0) {
            orderTotalTextView.setText("");
        } else if (quantity == 1) {
            orderTotalTextView.setText("$ " + total.toString() + " for " + quantity.toString() + " coffee.");
        } else {
            orderTotalTextView.setText("$ " + total.toString() + " for " + quantity.toString() + " coffees.");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_QUANTITY, mQuantity);
        savedInstanceState.putInt(KEY_TOTAL, mTotal);
    }
}
