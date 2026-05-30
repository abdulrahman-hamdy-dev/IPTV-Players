package com.myapp.iptv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ViewHolder> {
    private List<Channel> channelList;
    private OnChannelClickListener listener;

    public interface OnChannelClickListener { void onChannelClick(Channel channel); }

    public ChannelAdapter(List<Channel> list, OnChannelClickListener listener) {
        this.channelList = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Channel channel = channelList.get(position);
        holder.name.setText(channel.name);
        holder.itemView.setOnClickListener(v -> listener.onChannelClick(channel));
    }

    @Override
    public int getItemCount() { return channelList.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ViewHolder(View itemView) { super(itemView); name = itemView.findViewById(R.id.text_channel_name); }
    }
}

