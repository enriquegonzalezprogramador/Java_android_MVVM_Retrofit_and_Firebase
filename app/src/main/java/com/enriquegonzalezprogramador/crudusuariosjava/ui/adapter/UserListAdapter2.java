/*package com.enriquegonzalezprogramador.crudusuariosjava.ui.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.enriquegonzalezprogramador.crudusuariosjava.R;
import com.enriquegonzalezprogramador.crudusuariosjava.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter2 extends RecyclerView.Adapter<UserListAdapter2.UserViewHolder> implements UserClickListener{

    private ArrayList<User> userList;

    public UserListAdapter2(ArrayList<User> userList) {
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

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemDogBinding view = DataBindingUtil.inflate(inflater, R.layout.item_user, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.itemView.setUser(userList.get(position));
        holder.itemView.setListener(this);
    }

    @Override
    public void onUserClicked(View v) {
        String uuidString = ((TextView)v.findViewById(R.id.userId)).getText().toString();
        int uuid = Integer.valueOf(uuidString);
        ListFragmentDirections.ActionDetail action = ListFragmentDirections.actionDetail();
        action.setUserUuid(uuid);
        Navigation.findNavController(v).navigate(action);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        public ItemUserBinding itemView;

        public UserViewHolder(@NonNull ItemUserBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}*/

