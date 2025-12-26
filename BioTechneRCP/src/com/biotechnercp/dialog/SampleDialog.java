package com.biotechnercp.dialog;

import java.time.LocalDate;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.biotechnercp.model.Sample;

public class SampleDialog extends TitleAreaDialog {

    private Text nameText;
    private Text descriptionText;
    private DateTime dateTime;

    private Sample sample;

    public SampleDialog(Shell parentShell, Sample sample) {
        super(parentShell);
        this.sample = sample;
    }

    @Override
    protected Control createDialogArea(Composite parent) {
    	 Composite container = new Composite(parent, SWT.NONE);
         container.setLayoutData(new GridData(GridData.FILL_BOTH));

         GridLayout layout = new GridLayout(2, false);
         layout.marginWidth = 10;
         layout.marginHeight = 10;
         layout.horizontalSpacing = 10;
         container.setLayout(layout);

         Label nameLabel = new Label(container, SWT.NONE);
         nameLabel.setText("Name:");

         nameText = new Text(container, SWT.BORDER);
         nameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

         Label dateLabel = new Label(container, SWT.NONE);
         dateLabel.setText("Date:");

         dateTime = new DateTime(container, SWT.DATE | SWT.DROP_DOWN);

         Label descLabel = new Label(container, SWT.NONE);
         descLabel.setText("Description:");

         descriptionText = new Text(
                 container,
                 SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL
         );

         GridData descGridData = new GridData(GridData.FILL_BOTH);
         descGridData.heightHint = 80;
         descriptionText.setLayoutData(descGridData);

         if (sample.getName() != null) {
             nameText.setText(sample.getName());
         }

         if (sample.getDescription() != null) {
             descriptionText.setText(sample.getDescription());
         }

         if (sample.getDate() != null) {
             LocalDate d = sample.getDate();
             dateTime.setDate(d.getYear(), d.getMonthValue() - 1, d.getDayOfMonth());
         }

         return container;
    }

    @Override
    protected void okPressed() {
        sample.setName(nameText.getText());
        sample.setDescription(descriptionText.getText());
        sample.setDate(LocalDate.of(
            dateTime.getYear(),
            dateTime.getMonth() + 1,
            dateTime.getDay()
        ));
        super.okPressed();
    }

}
