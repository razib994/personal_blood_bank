package com.example.mediavision01.personal_blood_info;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BloodAdapterView extends RecyclerView.Adapter<BloodAdapterView.BloodViewHolder> implements Filterable {
    private List<Blood_Person_Info> blood_person_infos;
    private List<Blood_Person_Info> filteredList;
    Context context;

    public BloodAdapterView(List<Blood_Person_Info> blood_person_infos, Context context) {
        this.blood_person_infos = blood_person_infos;
        this.filteredList = blood_person_infos;
        this.context = context;

    }

    @Override
    public BloodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View views = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycler,parent,false);
        BloodViewHolder bloodViewHolder = new BloodViewHolder(views,context, (ArrayList<Blood_Person_Info>) blood_person_infos);
        return bloodViewHolder;
    }

    @Override
    public void onBindViewHolder(BloodViewHolder holder, final int position) {
        final Blood_Person_Info blood_person_info = filteredList.get(position);
        holder.nameTV.setText(filteredList.get(position).getBloodpersonName());
        holder.addressTV.setText(filteredList.get(position).getBloodpersonAddress());
        holder.bloodGroupTV.setText(filteredList.get(position).getPersonBloodGroup());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Delete This Person Information");
                alert.setMessage("Are You Sure to delete this item?");
                alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Blood_Person_DatabaseSource blood_person_databaseSource = new Blood_Person_DatabaseSource(context);
                        Boolean deleted = blood_person_databaseSource.deleteBloodGroup(blood_person_info.getId());
                        if(deleted){

                            Blood_Person_Info info = blood_person_infos.get(position);
                            blood_person_infos.remove(info);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Delete is Succesfull", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(context, "Deleted Is Not Successfull", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alert.setNegativeButton("Cancel",null);
                alert.show();


            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Blood_Person_Info blood_person_info = blood_person_infos.get(position);
                Intent goToUpdate = new Intent(context, UpdateRecordActivity.class);
                goToUpdate.putExtra("id", blood_person_info.getId());
                goToUpdate.putExtra("name",blood_person_info.getBloodpersonName());
                goToUpdate.putExtra("email",blood_person_info.getBloodpersonEmail());
                goToUpdate.putExtra("phone",blood_person_info.getBloodpersonPhone());
                goToUpdate.putExtra("gender",blood_person_info.getBloodpersongender());
                goToUpdate.putExtra("date",blood_person_info.getBloodpersondateofbirth());
                goToUpdate.putExtra("address",blood_person_info.getBloodpersonAddress());
                goToUpdate.putExtra("group",blood_person_info.getPersonBloodGroup());
                context.startActivity(goToUpdate);
            }
        });

    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString();
                List<Blood_Person_Info>tempList = new ArrayList<>();
                if(query.isEmpty()){
                    filteredList = blood_person_infos;
                }else{
                    for(Blood_Person_Info bloodPerson : blood_person_infos){
                        if(bloodPerson.getPersonBloodGroup().toLowerCase().contains(query.toLowerCase()) ||
                                bloodPerson.getBloodpersonName().toLowerCase().contains(query.toLowerCase())){
                            tempList.add(bloodPerson);
                        }
                    }
                    filteredList = tempList;
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (List<Blood_Person_Info>) filterResults.values;
                notifyDataSetChanged();//refresh recyclerview items
            }
        };
    }

    public class BloodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nameTV,emailTV,phoneTV,addressTV,genderTV,bloodGroupTV,dateofBirthTV;
        public ImageView delete,update;

        List<Blood_Person_Info> blood_person_infos = new ArrayList<Blood_Person_Info>();
        Context context;
        public BloodViewHolder(View itemView, Context context, ArrayList<Blood_Person_Info> blood_person_infos) {
            super(itemView);
            this.blood_person_infos = blood_person_infos;
            this.context = context;
            itemView.setOnClickListener(this);
            nameTV = itemView.findViewById(R.id.textViewName);
            addressTV = itemView.findViewById(R.id.textaddress);
            bloodGroupTV = itemView.findViewById(R.id.textViewBloodGroup);
            delete = itemView.findViewById(R.id.DeleteButton);
            update = itemView.findViewById(R.id.updateButton);

        }

        @Override
        public void onClick(View view) {
            int position =  getAdapterPosition();
            Blood_Person_Info blood_person_info = this.blood_person_infos.get(position);
            Intent intent = new Intent(this.context,DetailsPersonActivity.class);
            //intent.getSerializableExtra("blood",blood_person_info);
            intent.putExtra("id",blood_person_info.getId());
            intent.putExtra("name",blood_person_info.getBloodpersonName());
            intent.putExtra("email",blood_person_info.getBloodpersonEmail());
            intent.putExtra("phone",blood_person_info.getBloodpersonPhone());
            intent.putExtra("gender",blood_person_info.getBloodpersongender());
            intent.putExtra("dateofBirth",blood_person_info.getBloodpersondateofbirth());
            intent.putExtra("address",blood_person_info.getBloodpersonAddress());
            intent.putExtra("groupBlood",blood_person_info.getPersonBloodGroup());
            this.context.startActivity(intent);



        }
    }
}
