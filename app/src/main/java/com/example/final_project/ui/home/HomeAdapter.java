package com.example.final_project.ui.home;

        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.final_project.Database.BorrowersDB.Home_DataContact;
        import com.example.final_project.R;
        import com.example.final_project.firebaseConnection.ConnectionFireBase;

        import java.util.List;

class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private static final String TAG = "HomeAdapter";
    private List<Home_DataContact> mHomeDataContacts;
    ConnectionFireBase connection = new ConnectionFireBase();
    private View contactView;

    // Pass in the contact array into the constructor
    public HomeAdapter(List<Home_DataContact> homeDataContacts) {
        mHomeDataContacts = homeDataContacts;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        contactView = inflater.inflate(R.layout.home_template, parent, false);

        // Return a new holder instance
        return  new ViewHolder(contactView);
    }

    // Involves populating data into the item through holder
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Home_DataContact homeDataContact = mHomeDataContacts.get(position);
        final String email = homeDataContact.getId();
        connection.downloadProfileImage("prof_image/", email,holder.image,contactView);
       /* Glide.with(contactView)
                .load(homeDataContact.getImageUrl())
                .placeholder(R.drawable.account_pic)
                .into(holder.image);*/
        holder.name.setText(homeDataContact.getName());
        holder.date.setText(homeDataContact.getDate());
        holder.payed.setText("Payed : "+ homeDataContact.getPayed());
        holder.amount.setText("Amount : "+ homeDataContact.getAmount());
        Log.e(TAG,"Up and Done");
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mHomeDataContacts.size()-1;
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
