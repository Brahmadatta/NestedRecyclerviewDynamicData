package escapadetechnologies.com.nesterrecylerviewdynamicdata.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import escapadetechnologies.com.nesterrecylerviewdynamicdata.Model.Data;
import escapadetechnologies.com.nesterrecylerviewdynamicdata.R;

public class RecylerviewDataAdapter extends RecyclerView.Adapter<RecylerviewDataAdapter.ItemRowHolder>{

    private List<Data> dataList;
    private Context context;

    public RecylerviewDataAdapter(List<Data> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,null);

        return new ItemRowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder itemRowHolder, int i) {

        String  sectionName = dataList.get(i).getTitle();

        List singleSectionItems = dataList.get(i).getSections();

        itemRowHolder.title.setText(sectionName);


        SectionListAdapter sectionListAdapter = new SectionListAdapter(singleSectionItems,context);
        itemRowHolder.recyclerView_list.setHasFixedSize(true);
        itemRowHolder.recyclerView_list.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        itemRowHolder.recyclerView_list.setAdapter(sectionListAdapter);

        itemRowHolder.recyclerView_list.setNestedScrollingEnabled(false);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder{

        TextView title;
        RecyclerView recyclerView_list;

        public ItemRowHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.itemTitle);
            recyclerView_list = itemView.findViewById(R.id.recycler_view_list);
        }
    }
}
