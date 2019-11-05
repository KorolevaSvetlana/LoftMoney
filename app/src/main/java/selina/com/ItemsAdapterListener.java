package selina.com;

public interface ItemsAdapterListener {

    public  void onItemClick(Item item, final int position);

    public void onItemLongClick(Item item, final int position);
}
