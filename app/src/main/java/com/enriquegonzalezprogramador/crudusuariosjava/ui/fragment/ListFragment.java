package com.enriquegonzalezprogramador.crudusuariosjava.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.enriquegonzalezprogramador.crudusuariosjava.R;
import com.enriquegonzalezprogramador.crudusuariosjava.ui.adapter.UserListAdapter;
import com.enriquegonzalezprogramador.crudusuariosjava.viewModel.ListViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment {

    private ListViewModel viewModel;
    private UserListAdapter userListAdapter = new UserListAdapter(new ArrayList<>());

    @BindView(R.id.userList) RecyclerView userList;
    @BindView(R.id.ListError) TextView listError;
    @BindView(R.id.loadingView) ProgressBar loadingView;
    @BindView(R.id.refreshlayout) SwipeRefreshLayout refreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        viewModel.refresh();
        userList.setLayoutManager(new LinearLayoutManager(getContext()));
        userList.setAdapter(userListAdapter);

        refreshLayout.setOnRefreshListener(() -> {
            userList.setVisibility(View.GONE);
            listError.setVisibility(View.GONE);
            loadingView.setVisibility(View.VISIBLE);
            viewModel.refreshBypassCache();
            refreshLayout.setRefreshing(false);
        });

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.users.observe(getViewLifecycleOwner(), users -> {
            if(users != null) {
                userList.setVisibility(View.VISIBLE);
                userListAdapter.updateUserList(users);
            }
        });

        viewModel.userLoadError.observe(getViewLifecycleOwner(), isError -> {
            listError.setVisibility(isError ? View.VISIBLE : View.GONE);
        });

        viewModel.loading.observe(getViewLifecycleOwner(), isLoading -> {
            loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            if(isLoading) {
                listError.setVisibility(View.GONE);
                userList.setVisibility(View.GONE);
            }
        });
    }
}
