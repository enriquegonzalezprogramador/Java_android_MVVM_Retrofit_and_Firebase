package com.enriquegonzalezprogramador.crudusuariosjava.ui.adapter;

import static com.enriquegonzalezprogramador.crudusuariosjava.util.Util.getProgressDrawable;
import static com.enriquegonzalezprogramador.crudusuariosjava.util.Util.loadImage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.enriquegonzalezprogramador.crudusuariosjava.model.user.User;
import com.enriquegonzalezprogramador.crudusuariosjava.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private List<User> userList;

    public UserListAdapter(List<User> userList) {
        this.userList = userList;
    }

    public void updateUserList(List<User> newUserList) {
        userList.clear();
        userList.addAll(newUserList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bind(user);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.userId) TextView userIdTextView;
        @BindView(R.id.name) TextView userNameTextView;
        @BindView(R.id.imageView) ImageView userImageTextView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(User user) {
            userIdTextView.setText(user.breedId);
            userNameTextView.setText(user.dogBreed);

            CircularProgressDrawable progressDrawable = getProgressDrawable(itemView.getContext());
            loadImage(userImageTextView, user.imageUrl, progressDrawable);
        }
    }
}
