class recycleradapter extends RecyclerView.Adapter<recycleradapter.MyViewHolder> {

    @NonNull
    @Override
    public recycleadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view view LayoutInflater.from(parent.getContext()).inflate(R.layout.cardLayout, null);
        recycleradapter.MyViewHolder viewHolder = new recycleradapter.myviewelder(view);
        return viewHolder;
    }

    @NonNull
    @Override
    public void onBindviewHolder(@NonNull recycleadapter.MyViewHolder holder, int position) {
    }

    @Override

    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View iteeView) {
            super(itemview);
        }
    }
    private void listingdata() {
        ApiInterface apiInterface=Retrofit.getRetrofit().create(ApiInterface.class)
    }
    Call<Pojo> listingdata = apiInterface.getData()
            listingdata.enqueue(Callback<Pojo>(){

    })
}

public recycleradapter(list:List<Data>){
    this.list=list;
}

 Picasso.with(getApplicationContext())
        .load(list.get(position).avatar)
        .placeholder(R.drawable.ic_launcher_background)
                .fit()
                .into(holder.img)