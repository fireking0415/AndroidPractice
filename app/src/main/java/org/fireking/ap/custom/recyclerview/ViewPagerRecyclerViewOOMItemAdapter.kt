package org.fireking.ap.custom.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewpager_recyclerview_oom_item.view.*
import org.fireking.ap.R

class ViewPagerRecyclerViewOOMItemAdapter(title: String) :
    RecyclerView.Adapter<ViewPagerRecyclerViewOOMItemAdapter.ViewPagerRecyclerViewOOMItemViewHolder>() {

    private val dataList = ArrayList<ImageBean>()

    init {
        when (title) {
            "财富" -> {
                dataList.add(
                    ImageBean(
                        "$title-1",
                        "https://wxt.sinaimg.cn/mw1024/006hHB37ly1g6q0tznscjj30j60mbtc8.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-2",
                        "https://wxt.sinaimg.cn/mw1024/6e2a5d4dly1g7lzejjq52j20u0139wjn.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-3",
                        "https://wxt.sinaimg.cn/mw1024/6e2a5d4dly1g7lzek4exfj20u0137428.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-4",
                        "https://wxt.sinaimg.cn/mw1024/6e2a5d4dly1g7lzekqjxij20u0139q67.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-5",
                        "https://wxt.sinaimg.cn/mw1024/6e2a5d4dly1g7lzelj0cdj20u01300yf.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-6",
                        "https://wxt.sinaimg.cn/mw1024/6e2a5d4dly1g7lzem17vpj21sg2cl49f.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-7",
                        "https://wxt.sinaimg.cn/mw1024/6e2a5d4dly1g7lzemmtyij21sg2c37h0.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-8",
                        "https://wxt.sinaimg.cn/mw1024/007tJ2KEly1g1fdoylzadj30jg0sejto.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-9",
                        "https://wxt.sinaimg.cn/mw1024/9d52c073gy1ght0dylcsnj20j60ny0ys.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-10",
                        "https://wxt.sinaimg.cn/mw1024/9d52c073gy1ghpkp0afzcj20sg0w841g.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-11",
                        "https://wxt.sinaimg.cn/mw1024/9d52c073gy1ghpkoz508gj20sg0zkdqd.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-12",
                        "https://wxt.sinaimg.cn/mw1024/9d52c073gy1ghpkoz68vaj20oz0v844f.jpg"
                    )
                )
            }
            "故事" -> {
                dataList.add(
                    ImageBean(
                        "$title-1",
                        "https://wxt.sinaimg.cn/mw1024/9d52c073gy1ghpkoz47o0j20sg0yg10a.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-2",
                        "https://wxt.sinaimg.cn/mw1024/9d52c073gy1ghpkoz7sj2j20sg0zkqe6.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-3",
                        "https://wxt.sinaimg.cn/mw1024/9d52c073gy1ghpkozql0sj20sg0ya7i2.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-4",
                        "https://wxt.sinaimg.cn/mw1024/5c596b7ely3ghn1gcr28aj216o1kw7wj.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-5",
                        "https://wxt.sinaimg.cn/mw1024/9d52c073gy1ghpkoziphtj20sg0yqn7a.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-6",
                        "https://wxt.sinaimg.cn/mw1024/9d52c073gy1ghpkozbyrvj20sg0z0woj.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-7",
                        "https://wxt.sinaimg.cn/mw1024/7ec5929cly1ghksvd3b5sj21yp2ma000.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-8",
                        "https://wxt.sinaimg.cn/mw1024/7ec5929cly1ghksvbhtk2j22022o3e87.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-9",
                        "https://wxt.sinaimg.cn/mw1024/7ec5929cly1ghksvgjiarj21yo2m7x6u.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-10",
                        "https://wxt.sinaimg.cn/mw1024/7ec5929cly1ghksvjykgej222z2rz4qr.jpg"
                    )
                )
            }
            "Python" -> {
                dataList.add(
                    ImageBean(
                        "$title-1",
                        "https://wxt.sinaimg.cn/mw1024/7ec5929cly1ghksvl9f8qj21r12c14qu.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-2",
                        "https://wxt.sinaimg.cn/mw1024/7ec5929cly1ghkswx2muoj21qp2bm1l3.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-3",
                        "https://wxt.sinaimg.cn/mw1024/7ec5929cly1ghksvovon3j21ti2fcx6t.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-4",
                        "https://wxt.sinaimg.cn/mw1024/7ec5929cly1ghkswyb8x1j223g2smqv6.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-5",
                        "https://wxt.sinaimg.cn/mw1024/7ec5929cly1ghksvesruqj220p2oxkjr.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-6",
                        "https://wxt.sinaimg.cn/mw1024/0061VhPpgy1g3jiqbeyjdj30u011iq97.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-7",
                        "https://wxt.sinaimg.cn/mw1024/0061VhPpgy1g3jiqcalqcj30u01060zy.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-8",
                        "https://wxt.sinaimg.cn/mw1024/0061VhPpgy1g3jiqde9avj30u00tzq8g.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-9",
                        "https://wxt.sinaimg.cn/mw1024/0061VhPpgy1g3jiqdvv52j30u0106gs1.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-10",
                        "https://wxt.sinaimg.cn/mw1024/0061VhPpgy1g3jiqejng5j30u00u0tfs.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-11",
                        "https://wxt.sinaimg.cn/mw1024/0061VhPpgy1g3jiqez7gvj30u0106dmm.jpg"
                    )
                )
            }
            "Java" -> {
                dataList.add(
                    ImageBean(
                        "$title-1",
                        "https://wxt.sinaimg.cn/mw1024/0061VhPpgy1g3jiqfmbnkj30u0109dnl.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-2",
                        "https://wxt.sinaimg.cn/mw1024/0061VhPpgy1g3jiqgcauij30u010d46l.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-3",
                        "https://wxt.sinaimg.cn/mw1024/0061VhPpgy1g3jiqavifij30u010dgts.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-4",
                        "https://wxt.sinaimg.cn/mw1024/007LK0X3gy1gfrunr9etsj30u011h101.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-5",
                        "https://wxt.sinaimg.cn/mw1024/007LK0X3gy1gfrunrse0mj30u00x6ady.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-6",
                        "https://wxt.sinaimg.cn/mw1024/007LK0X3gy1gfruns9u8qj30u010ugtd.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-7",
                        "https://wxt.sinaimg.cn/mw1024/007LK0X3gy1gfrunsxaucj30u011iwk5.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-8",
                        "https://wxt.sinaimg.cn/mw1024/007LK0X3gy1gfruntavx1j30u011ijvn.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-9",
                        "https://wxt.sinaimg.cn/mw1024/007LK0X3gy1gfrunttxurj30u011i0vz.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-10",
                        "https://wxt.sinaimg.cn/mw1024/007LK0X3gy1gfrunuhigzj30u011haft.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-11",
                        "https://wxt.sinaimg.cn/mw1024/007LK0X3gy1gfrunuutixj30u011i78w.jpg"
                    )
                )
            }
            "OOM" -> {
                dataList.add(
                    ImageBean(
                        "$title-1",
                        "https://wxt.sinaimg.cn/mw1024/007LK0X3gy1gfrunvfllbj30u00zmaif.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-2",
                        "https://wxt.sinaimg.cn/mw1024/00676bwegy1g3oafo2o5sj30u011iwim.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-3",
                        "https://wxt.sinaimg.cn/mw1024/00676bwegy1g3oafohpk2j30u011iac3.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-4",
                        "https://wxt.sinaimg.cn/mw1024/00676bwegy1g3oafouz89j30u011imze.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-5",
                        "https://wxt.sinaimg.cn/mw1024/00676bwegy1g3oafp8l2lj30u011itb0.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-6",
                        "https://wxt.sinaimg.cn/mw1024/00676bwegy1g3oafpxyn2j30u011imzs.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-7",
                        "https://wxt.sinaimg.cn/mw1024/00676bwegy1g3oafqkrw7j30u011ijw1.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-8",
                        "https://wxt.sinaimg.cn/mw1024/00676bwegy1g3oafr6d1qj30u011i76n.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-9",
                        "https://wxt.sinaimg.cn/mw1024/00676bwegy1g3oafrpv2rj30u011iacn.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-10",
                        "https://wxt.sinaimg.cn/mw1024/00676bwegy1g3oafsbox7j30u011itbz.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-11",
                        "https://wxt.sinaimg.cn/mw1024/007DiWL6ly1g4xzq6jv41j30sg0zk0wi.jpg"
                    )
                )
            }
            else -> {
                dataList.add(
                    ImageBean(
                        "$title-1",
                        "https://wxt.sinaimg.cn/mw1024/007DiWL6ly1g4xzq6r69pj30sg0zkq8f.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-2",
                        "https://wxt.sinaimg.cn/mw1024/007DiWL6ly1g4xzq6mp3bj30sg0zkq7k.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-3",
                        "https://wxt.sinaimg.cn/mw1024/007DiWL6ly1g4xzq6zwkmj30sg0zjgqd.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-4",
                        "https://wxt.sinaimg.cn/mw1024/007DiWL6ly1g4xzq6n7m5j30sg0zkwk4.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-5",
                        "https://wxt.sinaimg.cn/mw1024/007DiWL6ly1g4xzq7g8vsj30sg0zk447.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-6",
                        "https://wxt.sinaimg.cn/mw1024/007DiWL6ly1g4xzq763bcj30sg0zk7a3.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-7",
                        "https://wxt.sinaimg.cn/mw1024/007DiWL6ly1g4xzq6o1xqj30sg0zkn2i.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-8",
                        "https://wxt.sinaimg.cn/mw1024/007DiWL6ly1g4xzq6pde6j30sg0zj79w.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-9",
                        "https://wxt.sinaimg.cn/mw1024/006CjOb2ly3g7u3j4af8dj30u0140wma.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-10",
                        "https://wxt.sinaimg.cn/mw1024/006CjOb2ly3g7u3j4nkm1j30u0140n56.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-11",
                        "https://wxt.sinaimg.cn/mw1024/006CjOb2ly3g7u3j5357pj30u0140469.jpg"
                    )
                )
                dataList.add(
                    ImageBean(
                        "$title-12",
                        "https://wxt.sinaimg.cn/mw1024/006CjOb2ly3g7u3j5il21j30u0140k0l.jpg"
                    )
                )
            }
        }

    }

    class ViewPagerRecyclerViewOOMItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), LayoutContainer {

        fun bindData(imageBean: ImageBean) {
            Glide.with(itemView.context).load(imageBean.image).into(itemView.imageView)
            itemView.textview.text = imageBean.title
        }

        override val containerView: View?
            get() = itemView
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerRecyclerViewOOMItemViewHolder {
        return ViewPagerRecyclerViewOOMItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.viewpager_recyclerview_oom_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewPagerRecyclerViewOOMItemViewHolder, position: Int) {
        holder.bindData(dataList[position])
    }
}