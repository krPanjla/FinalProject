package com.example.final_project.ui.home;

        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.recyclerview.widget.RecyclerView;

        import com.bumptech.glide.Glide;
        import com.example.final_project.Database.BorrowersDB.DataContact;
        import com.example.final_project.R;

        import java.util.List;

class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private static final String TAG = "HomeAdapter";
    private List<DataContact> mDataContacts;
    private View contactView;

    // Pass in the contact array into the constructor
    public HomeAdapter(List<DataContact> dataContacts) {
        mDataContacts = dataContacts;
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        contactView = inflater.inflate(R.layout.home_template, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        DataContact dataContact = mDataContacts.get(position);
        Glide.with(contactView)
                .load(dataContact.getImageUrl())
                .placeholder(R.drawable.account_pic)
                .into(holder.image);
        holder.name.setText(dataContact.getName());
        holder.date.setText(dataContact.getDate());
        holder.payed.setText("Payed : "+ dataContact.getPayed());
        holder.amount.setText("Amount : "+ dataContact.getAmount());
        Log.e(TAG,"Up and Done");
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mDataContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView name,date,amount,payed;
        public ImageView image;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            name = itemView.findViewById(R.id.home_name);
            image = itemView.findViewById(R.id.home_image);
            date = itemView.findViewById(R.id.home_date);
            payed = itemView.findViewById(R.id.home_payed);
            amount = itemView.findViewById(R.id.home_amount);
        }
    }
}
