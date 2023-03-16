package com.example.jiranismart.Fermented;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.jiranismart.R;

import java.util.ArrayList;

public class BorrowAda extends ArrayAdapter<LoanModel> {
    public ArrayList<LoanModel> MainList;
    public ArrayList<LoanModel> SubjectListTemp;
    public SubjectDataFilter subjectDataFilter;

    public BorrowAda(Context context, int id, ArrayList<LoanModel> subjectArrayList) {

        super(context, id, subjectArrayList);

        this.SubjectListTemp = new ArrayList<>();

        this.SubjectListTemp.addAll(subjectArrayList);

        this.MainList = new ArrayList<>();

        this.MainList.addAll(subjectArrayList);
    }

    @Override
    public Filter getFilter() {

        if (subjectDataFilter == null) {

            subjectDataFilter = new SubjectDataFilter();
        }
        return subjectDataFilter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.lo, null);

            holder = new ViewHolder();

            holder.type = convertView.findViewById(R.id.loanertyp);
            holder.amount = convertView.findViewById(R.id.lonAmt);
            holder.status = convertView.findViewById(R.id.brwLn);
            holder.count = convertView.findViewById(R.id.counter);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        LoanModel subject = SubjectListTemp.get(position);
        int pos = position + 1;
        holder.count.setText("(" + pos + ")");
        pos++;
        holder.type.setText(subject.getLoan_type());
        holder.amount.setText("KES" + subject.getAmount());
        holder.amount.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dealyed));
        holder.status.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.blink));
        return convertView;

    }

    public class ViewHolder {
        TextView type;
        TextView amount;
        TextView status;
        TextView count;
    }

    private class SubjectDataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {
                ArrayList<LoanModel> arrayList1 = new ArrayList<>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    LoanModel subject = MainList.get(i);

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

            SubjectListTemp = (ArrayList<LoanModel>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = SubjectListTemp.size(); i < l; i++)
                add(SubjectListTemp.get(i));
            notifyDataSetInvalidated();
        }
    }

}
