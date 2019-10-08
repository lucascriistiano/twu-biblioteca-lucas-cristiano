package com.twu.biblioteca.actions.movies;

import com.twu.biblioteca.actions.ListItems;
import com.twu.biblioteca.domain.Movie;
import com.twu.biblioteca.service.ItemService;
import com.twu.biblioteca.ui.OutputBuilder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListMovies extends ListItems<Movie> {

    public ListMovies(OutputBuilder outputBuilder, ItemService<Movie> service, String itemName) {
        super(outputBuilder, service, itemName);
    }

    @Override
    public void printFormattedItemsList(List<Movie> movies) {
        String format = generateMoviePrintFormat(movies);

        getOutputBuilder().addLine(String.format(format, "ID", "NAME", "YEAR", "DIRECTOR", "RATING"));
        movies.forEach(movie -> getOutputBuilder().addLine(String.format(format, movie.getId(), movie.getName(),
                movie.getYear(), movie.getDirector(), movie.getRating())));
    }

    private String generateMoviePrintFormat(List<Movie> movies) {
        int longestName = Collections.max(movies, Comparator.comparing(m -> m.getName().length())).getName().length();
        int longestDirector = Collections.max(movies, Comparator.comparing(m -> m.getDirector().length())).getDirector().length();

        return String.format("%%-4s %%-%ds  %%-4s  %%-%ds  %%-2s", longestName, longestDirector);
    }

}
