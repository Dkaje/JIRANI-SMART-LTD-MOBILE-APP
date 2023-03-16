package com.example.jiranismart.Fermented;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.jiranismart.R;

import java.util.ArrayList;

public class AliAda extends ArrayAdapter<AliModel> {
    public ArrayList<AliModel> MainList;
    public ArrayList<AliModel> SubjectListTemp;
    public AliAda.SubjectDataFilter subjectDataFilter;

    public AliAda(Context context, int id, ArrayList<AliModel> subjectArrayList) {

        super(context, id, subjectArrayList);

        this.SubjectListTemp = new ArrayList<>();

        this.SubjectListTemp.addAll(subjectArrayList);

        this.MainList = new ArrayList<>();

        this.MainList.addAll(subjectArrayList);
    }

    @Override
    public Filter getFilter() {

        if (subjectDataFilter == null) {

            subjectDataFilter = new AliAda.SubjectDataFilter();
        }
        return subjectDataFilter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AliAda.ViewHolder holder = null;

        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.loaner, null);

            holder = new AliAda.ViewHolder();

            holder.Name = convertView.findViewById(R.id.attachedName);
            holder.loan = convertView.findViewById(R.id.attachedAmount);
            holder.Phone = convertView.findViewById(R.id.attachedPhone);

            convertView.setTag(holder);

        } else {
            holder = (AliAda.ViewHolder) convertView.getTag();
        }

        AliModel subject = SubjectListTemp.get(position);
        int pos = position + 1;
        holder.Name.setText(+pos + ".  " + subject.getId_no());
        pos++;
        holder.Phone.setText("KES" + subject.getTotal());
        holder.loan.setText(subject.getStatus());
        return convertView;
    }

    public class ViewHolder {
        TextView Name;
        TextView Phone;
        TextView loan;
    }

    private class SubjectDataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {
                ArrayList<AliModel> arrayList1 = new ArrayList<>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    AliModel subject = MainList.get(i);

                    if (subject.toString().toLowerCase().contains(charSequence))

                        arrayList1.add(subject);
                }
                filterResults.count = arrayList1.size();

                filterResults.values = arrayList1;
            } else {
                synchronized (this) {
                    filterResults.values = MainList;

                    filterResults.count = MainList.size();
                }
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            SubjectListTemp = (ArrayList<AliModel>) filterResults.values;
            notifyDataSetChanged();
            clear();
            for (int i = 0, l = SubjectListTemp.size(); i < l; i++)
                add(SubjectListTemp.get(i));
            notifyDataSetInvalidated();
        }
    }

}

