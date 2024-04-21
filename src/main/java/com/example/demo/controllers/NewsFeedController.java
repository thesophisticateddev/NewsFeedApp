package com.example.demo.controllers;

import atlantafx.base.controls.Notification;
import atlantafx.base.theme.Styles;
import atlantafx.base.util.Animations;
import com.example.demo.MainApplication;
import com.example.demo.client.INewsClient;
import com.example.demo.components.BasicCard;
import com.example.demo.dto.NewsResponse;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material2.Material2OutlinedAL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class NewsFeedController extends VBox {

    private final INewsClient client;
    @FXML
    private VBox cardDisplayArea;

    @FXML
    private Label loadNewsDataLabel;

    @FXML
    private ScrollPane cardDisplayScrollPane;

    private MainController mainController;

    public NewsFeedController() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://017b4a607ffb41e3819e50020e2b9dc1.api.mockbin.io/")
                .addConverterFactory(JacksonConverterFactory.create()).build();
        this.client = retrofit.create(INewsClient.class);
        initialize();
    }

    @NotNull
    private static Task<List<HBox>> getListTask(NewsResponse newsData, int cardsInRow) {
        Task<List<HBox>> cardData = new Task<>() {
            @Override
            protected List<HBox> call() throws Exception {

                List<BasicCard> basicCards = newsData.getResults().stream()
                        .map(r -> {
                            String description;
                            if (r.getDescription() == null) {
                                description = "";
                            }
                            else {
                                description = r.getDescription().length() > 100 ? r.getDescription()
                                        .substring(0, 100) : r.getDescription();
                            }
                            return new BasicCard(r.getTitle(), r.getContent(), description, r.getPubDate());
                        })
                        .toList();

                long totalResults = newsData.getTotalResults();
                assert cardsInRow > 0;
                long rowSize = totalResults / cardsInRow;
                ArrayList<HBox> rows = new ArrayList<>((int) rowSize);
                for (int i = 0; i < rowSize; i += cardsInRow) {
                    List<BasicCard> cards = basicCards.subList(i, i + cardsInRow);
                    HBox box = new HBox(10.0);
                    box.getChildren().addAll(cards);
                    rows.add(box);

                }
                return rows;
            }
        };
        cardData.run();
        return cardData;
    }

    public void injectMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void initialize() {

        if (this.cardDisplayArea == null) {
            this.cardDisplayArea = new VBox(10);
            this.cardDisplayArea.setFillWidth(true);
            this.cardDisplayArea.setPrefWidth(MainApplication.WIDTH);
        }

        if (this.loadNewsDataLabel == null) {
            this.loadNewsDataLabel = new Label("Loading news...");
        }

        loadData();
    }

    public void dispatchNewsLoadNotification() {
        Notification notification = new Notification("News refreshed", new FontIcon(Material2OutlinedAL.HELP_OUTLINE));
        notification.getStyleClass().addAll(Styles.ELEVATED_1, Styles.ACCENT);
        notification.setOnClose(evt -> Animations.flash(notification).playFromStart());

    }

    public void loadData() {
        Call<NewsResponse> responseCall = this.client.getNews();
        responseCall.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                NewsResponse data = response.body();
                assert data != null;
                System.out.println("Data received from API, total results " + data.getTotalResults());
                Platform.runLater(() -> {
                    createNewsCards(data, 5);
                    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                    loadNewsDataLabel.setText("Last Updated at: " + LocalDateTime.now());
                    dispatchNewsLoadNotification();
                });


            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable throwable) {
                System.out.println("Error occured getting data " + throwable.getMessage());
                Platform.runLater(() -> {
                    cardDisplayArea.getChildren().clear();
                });
            }
        });


    }

    public void createNewsCards(NewsResponse newsData, int cardsInRow) {
        System.out.println("Task started to create cards");
        Task<List<HBox>> cardData = getListTask(newsData, cardsInRow);
//        cardData.setOnRunning((stateEvent) -> System.out.println("Task to render cards is running"));
        cardData.setOnSucceeded((succeeded) -> {

            try {
                System.out.println("Setting the cards into the display area");

                List<HBox> renderedBox = cardData.get();
                Platform.runLater(() -> {
                    this.cardDisplayArea.getChildren().clear();
                    this.cardDisplayArea.getChildren().addAll(renderedBox);
                });

            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

        });


    }

}
