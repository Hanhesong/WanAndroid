package me.hhs.wanandroid;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import me.hhs.wanandroid.entity.ArticleBean;

/**
 * Created by KevinSong on 2019/7/22
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<ArticleBean> list;
    private Context mContext;

    public RecyclerViewAdapter(List<ArticleBean> list, Context context) {
        this.list = list;
        this.mContext = context;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvArticleTitle;
        ImageButton ibCollect;
        TextView tvArticleAuthor;
        TextView tvArticleCategory;
        TextView tvArtlcleDate;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvArticleTitle = itemView.findViewById(R.id.tv_homefragment_article_title);
            tvArticleAuthor = itemView.findViewById(R.id.tv_homefragment_article_author);
            tvArticleCategory = itemView.findViewById(R.id.tv_homefragment_article_category);
            tvArtlcleDate = itemView.findViewById(R.id.tv_homefragment_article_date);
            ibCollect = itemView.findViewById(R.id.ib_fragment_artitle_collect);
        }
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_home_fragment, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.tvArticleTitle.setText(list.get(i).getTitle());
        myViewHolder.tvArticleAuthor.setText(list.get(i).getAuthor());
        myViewHolder.tvArticleCategory.setText(list.get(i).getSuperCapterName() + "/"+list.get(i).getChapterName());
        myViewHolder.tvArtlcleDate.setText(list.get(i).getPublishTime()+"");
        Glide.with(mContext).load(R.drawable.selector_collect).into(myViewHolder.ibCollect);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
