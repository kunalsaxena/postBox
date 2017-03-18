package com.mailclient.postbox;

import com.mailclient.dto.EmailDTO;
import com.mailclient.utils.EmailReadUtil;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.List;


/**
 * @author kunal
 *         Controller for Form actions
 */
public class Controller {

    @FXML
    private TableView<EmailDTO> mailTableView;

    /*@FXML
    private TextArea contentView;

    @FXML
    private WebView webView;*/

    @FXML
    private void handleGetMailBtn(ActionEvent event) {

        TableColumn subjectCol = new TableColumn("Subject");
        subjectCol.setMinWidth(600);
        subjectCol.setCellValueFactory(new PropertyValueFactory<EmailDTO, String>("subject"));

        TableColumn fromCol = new TableColumn("From");
        fromCol.setMinWidth(400);
        fromCol.setCellValueFactory(new PropertyValueFactory<EmailDTO, String>("from"));

        // Getting real emails
        ObservableList<EmailDTO> data = FXCollections.observableArrayList();

        List<EmailDTO> emailList = EmailReadUtil.readInbox();
        for (EmailDTO email : emailList) {
            data.add(new EmailDTO(email.getSubject(), email.getFrom(), email.getContentType(), email.getContent()));
        }

        mailTableView.setItems(data);
        mailTableView.getColumns().addAll(subjectCol, fromCol);
    }

    @FXML
    private void showMailConent(MouseEvent event) {

        List<EmailDTO> emailList = EmailReadUtil.readInbox();
        EmailDTO emailDTO = mailTableView.getSelectionModel().getSelectedItem();

        // Open new window
        Stage stage = new Stage();
        stage.setTitle(emailDTO.getSubject());

        WebView wv = new WebView();
        wv.getEngine().loadContent(emailDTO.getContent());
        stage.setScene(new Scene(wv, 1000, 400));

        stage.show();
    }

    @FXML
    private void handleExitBtn(ActionEvent event) {
        Platform.exit();
    }

}
