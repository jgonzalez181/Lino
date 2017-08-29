package com.cclip.gui.schedule;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.cclip.gui.util.ComboItem;
import com.cclip.gui.util.JPanelList;
import com.cclip.model.schedule.Schedule;
import com.cclip.model.schedule.ScheduleBatch;
import com.cclip.services.Services;
import com.cclip.util.UtilComponent;

public class JPanelSchedule extends JPanelList {

	private static final long serialVersionUID = 8740413335514099146L;

	private JPanelScheduleBatchForm jPanelScheduleBatchForm;

	private JComboBox<ComboItem> jComboBoxSchedule;

	private JButton jButtonBuild;

	private JButton jButtonBuild2;

	private Schedule[] scheduleList;

	public JPanelSchedule() {

		initComponents();
	}

	private void initComponents() {

		jPanelScheduleBatchForm = new JPanelScheduleBatchForm();

		addColumnMetaData("id", String.class, 0);
		addColumnMetaData("Cronograma (año)", Integer.class, 130);
		addColumnMetaData("N°", Integer.class, 50);
		addColumnMetaData("Cerrado", Date.class, 90);
		addColumnMetaData("Entregado", Date.class, 90);
		addColumnMetaData("Comentario", Integer.class, -1);

		super.initComponents("Lote", jPanelScheduleBatchForm, initToolBar1(), null, new Color(147, 205, 221), new java.awt.Color(0, 32, 96));

	}

	@SuppressWarnings("unchecked")
	private Component[] initToolBar1() {

		jButtonBuild = new JButton("Nuevo cronograma");
		jButtonBuild.setToolTipText("Nuevo cronograma.");
		jButtonBuild.setIcon(new ImageIcon("/home/java/jaspe/cclip/icon/run-build.png")); // NOI18N
		// jButtonBuild.setPreferredSize(new Dimension(27, 27));

		jButtonBuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buildSchedule();
			}

		});

		jButtonBuild2 = new JButton("Nuevo lote");
		jButtonBuild2.setToolTipText("Nuevo lote.");
		jButtonBuild2.setIcon(new ImageIcon("/home/java/jaspe/cclip/icon/run-build.png")); // NOI18N
		// jButtonBuild2.setPreferredSize(new Dimension(27, 27));

		jButtonBuild2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buildScheduleBatch();
			}
		});

		JLabel jLabel1 = UtilComponent.buildJLabelField("-                 Crononograma:");
		jLabel1.setToolTipText("Buscar por año (cronograma)");
		jLabel1.setForeground(new Color(0, 32, 96));

		ActionListener actionListener1 = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				resetSearch();
			}
		};

		jComboBoxSchedule = new JComboBox<ComboItem>();
		jComboBoxSchedule.setToolTipText("Buscar por cronograma");
		scheduleLoadData();
		if (jComboBoxSchedule.getItemCount() > 0) {
			jComboBoxSchedule.setSelectedIndex(0);
		}

		class ItemChangeListener1 implements ItemListener {

			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {

					// ScheduleBatch scheduleBatch = new ScheduleBatch();
					//
					// scheduleBatch.setId(getScheduleBatchId());666

					searchData();
				}
			}
		}

		jComboBoxSchedule.addItemListener(new ItemChangeListener1());

		Component[] headerRowList = { jButtonBuild, jButtonBuild2, jLabel1, jComboBoxSchedule };

		return headerRowList;
	}

	// private String getScheduleBatchId(){
	//
	// return this.jScrollPaneJTable.jTable.getValueAt(jScrollPaneJTable.jTable.getSelectedRow(), 0).toString();
	// }

	public void clearFilter() {

		scheduleLoadData();

		if (jComboBoxSchedule.getItemCount() > 0) {
			jComboBoxSchedule.setSelectedIndex(0);
		}

	}

	protected void find(int offSet, int limit) throws Exception {

		String scheduleId = null;

		if (jComboBoxSchedule.getSelectedItem() != null) {
			scheduleId = ((ComboItem) jComboBoxSchedule.getSelectedItem()).getId();
		}

		rl = Services.getInstance().findScheduleBatchByExample(scheduleId, offSet, limit);

		if (rl == null) {
			return;
		}

		model = (Object[][]) rl.getList();

	}

	private void scheduleLoadData() {

		try {

			jComboBoxSchedule.removeAllItems();

			Object[][] table = Services.getInstance().findSchedule();
			scheduleList = new Schedule[table.length];

			for (int i = 0; i < table.length; i++) {
				Schedule schedule = new Schedule();
				schedule.setId(table[i][0].toString());
				if (table[i][1] != null) {
					schedule.setYear(Integer.valueOf(table[i][1].toString()));
				}
				scheduleList[i] = schedule;
			}

			if (scheduleList != null) {

				for (int i = 0; i < scheduleList.length; i++) {

					jComboBoxSchedule.addItem(new ComboItem(scheduleList[i].getYear().toString(), scheduleList[i].getId()));

				}
			}

			jComboBoxSchedule.addItem(new ComboItem("", null));

		} catch (Exception e) {

			UtilComponent.logger(e);
		}

	}

	private void buildSchedule() {
		try {

			// -----------------------------------------------------------------------
			int y = 0;

			if (scheduleList == null) {
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTimeInMillis(System.currentTimeMillis());

				y = gc.get(Calendar.YEAR);
			} else {

				y = scheduleList[0].getYear() + 1;

			}

			Object[] options = { "Si", "No" };
			int choice = JOptionPane.showOptionDialog(null, "Usted esta a punto de dar de alta un nuevo cronograma, el mismo sera " + y, "¿ Alta Nuevo Cronograma ?", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (choice == JOptionPane.YES_OPTION) {
				Schedule schedule = new Schedule();
				schedule.setId(UUID.randomUUID().toString());
				schedule.setErased(false);
				schedule.setYear(y);
				schedule.setDateFrom(UtilComponent.getDate("1", "1", "" + y));
				schedule.setDateTo(UtilComponent.getDate("31", "12", "" + y));

				Services.getInstance().insertSchedule(schedule);

				JOptionPane.showMessageDialog(null, "El cronograma se creo con éxito ! " + y);

				scheduleLoadData();

				this.searchData();
			}

			// -----------------------------------------------------------------------

		} catch (Exception e) {

			UtilComponent.logger(e);
		}

	}

	private void buildScheduleBatch() {
		try {

			// -----------------------------------------------------------------------
			int y = 0;

			if (scheduleList == null) {
				return;
			} else {

				y = scheduleList[jComboBoxSchedule.getSelectedIndex()].getYear();

			}

			Object[] options = { "Si", "No" };
			int choice = JOptionPane.showOptionDialog(null, "Usted esta a punto de dar de alta un nuevo lote, el mismo sera para el cronograma " + y, "¿ Alta Nuevo Lote?", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (choice == JOptionPane.YES_OPTION) {
				String comment = JOptionPane.showInputDialog(this, "Ingrese la descripción", "Descripción del lote", JOptionPane.QUESTION_MESSAGE);

				ScheduleBatch scheduleBatch = new ScheduleBatch();
				scheduleBatch.setId(UUID.randomUUID().toString());
				scheduleBatch.setErased(false);
				scheduleBatch.setSchedule(scheduleList[jComboBoxSchedule.getSelectedIndex()]);
				scheduleBatch.setCreateDate(new Date(System.currentTimeMillis()));
				scheduleBatch.setComment(comment);

				Services.getInstance().insertScheduleBatch(scheduleBatch);

				JOptionPane.showMessageDialog(null, "El lote para el cronograma " + y + " se creo con éxito !");

				this.searchData();
			}

			// -----------------------------------------------------------------------

		} catch (Exception e) {

			UtilComponent.logger(e);
		}

	}
}
