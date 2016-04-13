package pl.kodujdlapolski.na4lapy.ui.browse.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.ui.browse.OnBrowseElementClickedAction;

/**
 * Created by Natalia Wróblewska on 2016-02-27.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
public class ListBrowseRecyclerAdapter extends RecyclerView.Adapter<ListBrowseViewHolder> {
    private ArrayList<Animal> animals;
    private OnBrowseElementClickedAction onBrowseElementClickedAction;

    public ListBrowseRecyclerAdapter(ArrayList<Animal> animals, OnBrowseElementClickedAction onBrowseElementClickedAction) {
        this.animals = animals;
        this.onBrowseElementClickedAction = onBrowseElementClickedAction;
    }

    @Override
    public ListBrowseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListBrowseViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.view_holder_list_browse, parent, false));
    }

    @Override
    public void onBindViewHolder(ListBrowseViewHolder holder, int position) {
        holder.init(animals.get(position), onBrowseElementClickedAction);
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }
}
