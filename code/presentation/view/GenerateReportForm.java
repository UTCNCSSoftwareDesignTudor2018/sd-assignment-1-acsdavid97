package presentation.view;

import data_access.dto.Exam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collection;

public class GenerateReportForm {
    private JPanel rootPanel;
    private JTextField dateFromField;
    private JTextField dateToField;
    private JButton reportGenerate;
    private JList<Exam> examList;

    public GenerateReportForm() {
        examList.setPreferredSize(new Dimension(400, 400));
    }

    public void setReportGenerateActionListener(ActionListener actionListener) {
        reportGenerate.addActionListener(actionListener);
    }

    public String getDateFromText() {
        return dateFromField.getText();
    }

    public String getDateToText() {
        return dateToField.getText();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void populateExamList(Collection<Exam> examCollection) {
        Exam[] examArray = examCollection.toArray(new Exam[examCollection.size()]);
        examList.setListData(examArray);
    }
}
