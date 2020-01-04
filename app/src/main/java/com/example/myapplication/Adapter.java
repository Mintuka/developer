/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;


class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>  {

    // Member variables.
    private ArrayList<DataModel> LaptopsData;
    private Context context;

    Adapter(Context context, ArrayList<DataModel> LaptopsData) {
        this.LaptopsData = LaptopsData;
        this.context = context;
    }



    @Override
    public Adapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).
                inflate(R.layout.list_item, parent, false));
    }


    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder,
                                 int position) {
        // Get current sport.
        DataModel currentLaptop = LaptopsData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentLaptop);
    }

    @Override
    public int getItemCount() {
        return LaptopsData.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // Member Variables for the TextViews
        private TextView TitleText;
        private TextView InfoText;
        private ImageView LaptopsImage;
        @Override
        public void onClick(View view) {
            DataModel currentLaptop = LaptopsData.get(getAdapterPosition());
            Intent detailIntent = new Intent(context, Detail.class);
            detailIntent.putExtra("title", currentLaptop.getTitle());
            detailIntent.putExtra("image_resource",
                    currentLaptop.getImageResource());
            context.startActivity(detailIntent);
        }

        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            TitleText = itemView.findViewById(R.id.title);
            InfoText = itemView.findViewById(R.id.subTitle);
            LaptopsImage = itemView.findViewById(R.id.LaptopsImage);

            itemView.setOnClickListener(this);
        }

        void bindTo(DataModel currentLaptop){
            // Populate the textviews with data.
            TitleText.setText(currentLaptop.getTitle());
            InfoText.setText(currentLaptop.getInfo());
            Glide.with(context).load(currentLaptop.getImageResource()).into(LaptopsImage);
        }
    }
}
