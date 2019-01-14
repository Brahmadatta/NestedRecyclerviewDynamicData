package escapadetechnologies.com.nesterrecylerviewdynamicdata.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import escapadetechnologies.com.nesterrecylerviewdynamicdata.Model.Data;
import escapadetechnologies.com.nesterrecylerviewdynamicdata.Model.Section;
import escapadetechnologies.com.nesterrecylerviewdynamicdata.R;

public class SectionListAdapter extends RecyclerView.Adapter<SectionListAdapter.SectionViewHolder>{

    List<Section> sectionList;
    private Context context;

    public SectionListAdapter() {
    }

    public SectionListAdapter(List<Section> sectionList, Context context) {
        this.sectionList = sectionList;
        this.context = context;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card,null);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder sectionViewHolder, int i) {

        sectionViewHolder.title.setText(sectionList.get(i).getName());

        Glide.with(context).load(sectionList.get(i).getImage()).into(sectionViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView imageView;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            imageView = itemView.findViewById(R.id.imageview);
        }
    }

}
