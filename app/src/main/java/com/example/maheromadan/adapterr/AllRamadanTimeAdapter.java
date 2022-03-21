package com.example.maheromadan.adapterr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maheromadan.R;
import com.example.maheromadan.model.DistRamadanDetail;
import com.example.maheromadan.utils.GlobalMethods;

import java.util.ArrayList;

public class AllRamadanTimeAdapter extends RecyclerView.Adapter<AllRamadanTimeAdapter.ViewHolderClass> {
    ArrayList<DistRamadanDetail> distRamadanDetails;

    public AllRamadanTimeAdapter( ArrayList<DistRamadanDetail> distRamadanDetails){
        this.distRamadanDetails=distRamadanDetails;
    }
    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recv_time_list,parent,false);
        return new ViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {
        DistRamadanDetail distRamadanDetail=distRamadanDetails.get(position);
        holder.text_dosok.setText(distRamadanDetail.getRamadanPeriod());
        holder.text_tarik.setText(distRamadanDetail.getEngDate()+"\n"
        +distRamadanDetail.getBanglaDate()+"\n"+distRamadanDetail.getRamadanDate());
        holder.text_seheri.setText(convertEnToBn(distRamadanDetail.getSehri().substring(0,5)));
        holder.text_iftar.setText(convertEnToBn(distRamadanDetail.getIftar().substring(0,5)));

    }

    public  String convertEnToBn(String data) {
        return data.replaceAll("0", "\u09E6")
                .replaceAll("1", "\u09E7")
                .replaceAll("2", "\u09E8")
                .replaceAll("3", "\u09E9")
                .replaceAll("4", "\u09EA")
                .replaceAll("5", "\u09EB")
                .replaceAll("6", "\u09EC")
                .replaceAll("7", "\u09ED")
                .replaceAll("8", "\u09EE")
                .replaceAll("9", "\u09EF");
    }

    @Override
    public int getItemCount() {
        return distRamadanDetails.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView text_dosok,text_tarik,text_seheri,text_iftar;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            text_dosok=itemView.findViewById(R.id.text_dosok);
            text_seheri=itemView.findViewById(R.id.text_seheri);
            text_tarik=itemView.findViewById(R.id.text_tarik);
            text_iftar=itemView.findViewById(R.id.text_iftar);
        }
    }
}
