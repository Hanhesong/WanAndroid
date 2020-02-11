package me.hhs.wanandroid;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.List;
import me.hhs.wanandroid.entity.ArticleDataBean;
import me.hhs.wanandroid.ui.WebActivity;

/**
 * Created by KevinSong on 2019/7/22
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<ArticleDataBean.ArticleData.Article> list;
    private Context mContext;

    public RecyclerViewAdapter(List<ArticleDataBean.ArticleData.Article> list, Context context) {
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
    public void onBindViewHolder(@NonNull final RecyclerViewAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.tvArticleTitle.setText(list.get(i).getTitle());
        myViewHolder.tvArticleAuthor.setText(list.get(i).getAuthor());
        myViewHolder.tvArticleCategory.setText(list.get(i).getSuperChapterName() + "/" + list.get(i).getChapterName());
        myViewHolder.tvArtlcleDate.setText(list.get(i).getNiceDate());
        myViewHolder.ibCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myViewHolder.ibCollect.isSelected()) {
                    myViewHolder.ibCollect.setSelected(false);
                } else {
                    myViewHolder.ibCollect.setSelected(true);
                }
            }
        });
        //这个方法是加载相应图片的，暂时用不到。
        // Glide.with(mContext).load(R.drawable.selector_collect).into(myViewHolder.ibCollect);
        final String articleLink = list.get(i).getLink();

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("articleLink", articleLink);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
