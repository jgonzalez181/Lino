package com.cclip.gui.geo.cadastre;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.cclip.gui.schedule.census.JDialogCensusHeaderFormCustom;
import com.cclip.model.geo.cadastre.Cadastre;
import com.cclip.model.geo.cadastre.CadastreCensus;
import com.cclip.services.Services;
import com.cclip.util.UtilCadastre;
import com.cclip.util.UtilComponent;

public class JPanelCadastreViewCustom extends JPanelCadastreView {

	private static final long serialVersionUID = 8995432841822380519L;

	private JPanelCadastreViewCustomW jPanelCadastreViewCustomW;

	private Cadastre cadastre;
	private Object[][] cadastreBlockList = new Object[0][0];
	private Object[][] cadastreScanningList = new Object[0][0];
	private Object[][] cadastrePhList = new Object[0][0];
	private Object[][] cadastrePhBlockList = new Object[0][0];

	public JPanelCadastreViewCustom(JPanelCadastreViewCustomW jPanelCadastreViewCustomW) {
		super();

		this.jPanelCadastreViewCustomW = jPanelCadastreViewCustomW;
		printClear();

		jTablePhList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		jTablePhList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				try {

					if (cadastrePhList != null && cadastrePhList.length > 0 && jTablePhList.getSelectedRow() > -1 && event.getValueIsAdjusting() == false) {

						int r = jTablePhList.getSelectedRow();

						findBlokPhList(cadastrePhList[r][0].toString());

					}

				} catch (Exception e) {

					UtilComponent.logger(e);

				}
			}
		});

		jPanelInmAddres.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editInmAddressCustom();
				}
			}
		});

		jTextPaneInmAddres.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editInmAddressCustom();
				}
			}
		});

		jLabel111.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editUfDesc();
				}
			}
		});

		jLabelUfId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editUfDesc();
				}
			}
		});

		jTextPaneUfDesc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editUfDesc();
				}
			}
		});

		jPanelUfDesc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editUfDesc();
				}
			}
		});

		jPanelUserWaterDesc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editUser();
				}
			}
		});

		jTextPaneUserWaterDesc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editUser();
				}
			}
		});
		
		//---------------HEADER CENSUS-----------------------------------------
		jPanel6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editHeader();
				}
			}
		});

		// ---------------------------------------------------

		jPanelGeneral.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabel57.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabel71.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabel107.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabel101.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabel103.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabel2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelCadastreType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelCadastreSituation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelWaterMeter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelDateCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelDateDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelReasonLow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jPanel6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabel97.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelM2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelM2Percent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabel100.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelM2CoveredBlock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabel119.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelM2CoveredShared.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelM2SharedPercent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabel102.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelM2CoveredPh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelM2PHPercent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabel104.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelM2CoveredTotal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelM2PercentTotal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelCadastralCode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabel11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelCtaCli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelCityArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabel15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabel17.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelDv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		jLabelSubCtaCli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editGeneral();
				}
			}
		});

		// ---------------------------------------------------

		jTableBlockList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editCadastreBlock();
				}
			}
		});
		
		jScrollPaneCadastreBlockList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editCadastreBlock();
				}
			}
		});
	}

	public void setDto(Object dto, JTable jTable, Integer row) {

		if (dto == null) {
			return;
		}

		Object[] objArray = (Object[]) dto;

		this.setCadastre(Services.getInstance().findCadastreById(objArray[0].toString()));
	}

	public void setCadastreCensusId(String cadastreCensusId) {

		if (cadastreCensusId == null) {
			return;
		}

		this.setCadastre(Services.getInstance().findCadastreCensusById(cadastreCensusId));
	}

	public void setCadastreId(String cadastreId) {

		if (cadastreId == null) {
			return;
		}

		this.setCadastre(Services.getInstance().findCadastreById(cadastreId));
	}

	public void setCadastre(Cadastre cadastre) {
		this.cadastre = cadastre;
		print();
	}

	public Cadastre getCadastre() {
		return cadastre;
	}

	private void print() {

		printClear();

		if (this.cadastre == null) {
			return;
		}

		if (cadastre instanceof CadastreCensus) {
			jPanelAudit.setVisible(false);
		}

		printGeneral();

		// if (cadastre.getM2Percent() != null) { no va en PH madre o PV siempre es null
		// this.jLabelM2Percent.setText(cadastre.getM2Percent().toString());
		// } else {
		// this.jLabelM2Percent.setText("");
		// }

		printInmAddres();

		printUser();

		if (cadastre.getAuditVersion() != null) {
			this.jLabelAuditVersion.setText(cadastre.getAuditVersion().toString());
		} else {
			this.jLabelAuditVersion.setText("");
		}

		if (cadastre.getAuditVersionCodeLabel() != null) {
			this.jLabelAuditVersionCodeLabel.setText(cadastre.getAuditVersionCodeLabel());
		} else {
			this.jLabelAuditVersionCodeLabel.setText("");
		}

		if (cadastre.getAuditVersionLabel() != null) {
			this.jLabelAuditVersionLabel.setText(cadastre.getAuditVersionLabel());
		} else {
			this.jLabelAuditVersionLabel.setText("");
		}

		if (cadastre.getAuditDateCreate() != null) {
			// this.jLabelAuditDateCreate.setText(new SimpleDateFormat("dd/MM/yyyy").format(cadastre.getAuditDateCreate()));
			this.jLabelAuditDateCreate.setText(cadastre.getAuditDateCreate().toLocaleString());
		} else {
			this.jLabelAuditDateCreate.setText("");
		}

		if (cadastre.getAuditUserCreate() != null) {
			this.jLabelAuditUserCreate.setText(cadastre.getAuditUserCreate());
		} else {
			this.jLabelAuditUserCreate.setText("");
		}

		if (cadastre.getAuditDateUpdate() != null) {
			// this.jLabelAuditDateUpdate.setText(new SimpleDateFormat("dd/MM/yyyy").format(cadastre.getAuditDateUpdate()));
			this.jLabelAuditDateUpdate.setText(cadastre.getAuditDateUpdate().toLocaleString());
		} else {
			this.jLabelAuditDateUpdate.setText("");
		}

		if (cadastre.getAuditUserUpdate() != null) {
			this.jLabelAuditUserUpdate.setText(cadastre.getAuditUserUpdate());
		} else {
			this.jLabelAuditUserUpdate.setText("");
		}

		if (cadastre.getId() != null) {
			this.jTextPaneId.setText(cadastre.getId());
		} else {
			this.jTextPaneId.setText("");
		}

		if (cadastre.getScheduleScanningResult2() != null) {
			this.jLabelCr2.setText(cadastre.getScheduleScanningResult2().getId());
		} else {
			this.jLabelCr2.setText("   ");
		}

		if (cadastre.getScheduleScanningResult1() != null) {
			this.jLabelCr1.setText(cadastre.getScheduleScanningResult1().getId());
		} else {
			this.jLabelCr1.setText("   ");
		}

		if (cadastre.getScheduleScanningResult0() != null) {
			this.jLabelCr0.setText(cadastre.getScheduleScanningResult0().getId());
		} else {
			this.jLabelCr0.setText("   ");
		}

		if (cadastre.getDateScanning2() != null) {
			this.jLabelDateScanning2.setText(new SimpleDateFormat("dd/MM/yyyy").format(cadastre.getDateScanning2()));
		} else {
			this.jLabelDateScanning2.setText("   ");
		}

		if (cadastre.getDateScanning1() != null) {
			this.jLabelDateScanning1.setText(new SimpleDateFormat("dd/MM/yyyy").format(cadastre.getDateScanning1()));
		} else {
			this.jLabelDateScanning1.setText("   ");
		}

		if (cadastre.getDateScanning0() != null) {
			this.jLabelDateScanning0.setText(new SimpleDateFormat("dd/MM/yyyy").format(cadastre.getDateScanning0()));
		} else {
			this.jLabelDateScanning0.setText("   ");
		}

		if (cadastre.getCantPh() != null) {
			this.jLabelCantPh.setText(cadastre.getCantPh().toString());
		} else {
			this.jLabelCantPh.setText("");
		}

		if (cadastre.getCadastreSubDivisionType() != null) {
			this.jLabelCadastreSubDivisionType.setText(cadastre.getCadastreSubDivisionType().toString());
		} else {
			this.jLabelCadastreSubDivisionType.setText("");
		}

		if (cadastre.getCadastreActivityType() != null) {
			this.jLabelCadastreActivityType.setText(cadastre.getCadastreActivityType().toString());
		} else {
			this.jLabelCadastreActivityType.setText("");
		}

		if (cadastre.getCadastreType().getId().equals("BA") && cadastre.getM2Covered() != null && cadastre.getM2Covered() > 0) {
			this.jLabelM2Covered.setForeground(Color.red);
		} else {
			this.jLabelM2Covered.setForeground(Color.BLACK);
		}

		find();
	}

	private void printGeneral() {
		if (this.cadastre.getCadastralCode() != null) {
			
			if (this.cadastre.getCadastreType() != null) {
				this.jLabelCadastralCode.setText(cadastre.getCadastreType().getId() + ": " + UtilCadastre.formatCadastralCode(this.cadastre.getCadastralCode()));
			} else {
				this.jLabelCadastralCode.setText(UtilCadastre.formatCadastralCode(this.cadastre.getCadastralCode()));
			}
			
		} else {
			this.jLabelCadastralCode.setText("");
		}

		printUf();

		if (this.cadastre.getCtaCli() != null) {
			this.jLabelCtaCli.setText(this.cadastre.getCtaCli());

		} else {
			this.jLabelCtaCli.setText("");
		}

		if (this.cadastre.getCityArea() != null) {
			this.jLabelCityArea.setText(this.cadastre.getCityArea().toString());
		} else {
			this.jLabelCityArea.setText("");
		}

		jLabelSubCtaCli.setForeground(Color.BLACK);

		if (this.cadastre.getSubCtaCli() != null) {
			this.jLabelSubCtaCli.setText(this.cadastre.getSubCtaCli());

			if (cadastre.getCadastralCode() != null && cadastre.getCadastralCode().trim().endsWith(this.cadastre.getSubCtaCli().trim()) == false) {
				jLabelSubCtaCli.setForeground(Color.RED);
			} else {
				jLabelSubCtaCli.setForeground(Color.BLACK);
			}
		} else {
			this.jLabelSubCtaCli.setText("");
		}

		if (this.cadastre.getDv() != null) {
			this.jLabelDv.setText(this.cadastre.getDv());
		} else {
			this.jLabelDv.setText("");
		}

		if (cadastre.getCadastreType() != null) {
			this.jLabelCadastreType.setText(this.cadastre.getCadastreType().toString());
		} else {
			this.jLabelCadastreType.setText("");
		}

		if (cadastre.getCadastreSituation() != null) {
			this.jLabelCadastreSituation.setText(this.cadastre.getCadastreSituation().toString());
		} else {
			this.jLabelCadastreSituation.setText("");
		}

		if (cadastre.getWaterMeter() != null && cadastre.getWaterMeter() == true) {

			if (cadastre.getWaterMeterType() != null) {
				this.jLabelWaterMeter.setText("Si - " + cadastre.getWaterMeterType());
			} else {
				this.jLabelWaterMeter.setText("Si");
			}

		} else if (cadastre.getWaterMeter() != null && cadastre.getWaterMeter() == false) {
			this.jLabelWaterMeter.setText("No");
		} else {
			this.jLabelWaterMeter.setText("");
		}

		if (cadastre.getDateCreate() != null) {
			this.jLabelDateCreate.setText(new SimpleDateFormat("dd/MM/yyyy").format(cadastre.getDateCreate()));
			// this.jLabelDateCreate.setText(cadastre.getDateCreate().toLocaleString());
		} else {
			this.jLabelDateCreate.setText("");
		}

		if (cadastre.getDateDelete() != null) {
			this.jLabelDateDelete.setText(new SimpleDateFormat("dd/MM/yyyy").format(cadastre.getDateDelete()));
			// this.jLabelDateDelete.setText(cadastre.getDateDelete().toLocaleString());
		} else {
			this.jLabelDateDelete.setText("");
		}

		if (cadastre.toStringReasonLow() != null) {
			this.jLabelReasonLow.setText(cadastre.toStringReasonLow());
		} else {
			this.jLabelReasonLow.setText("");
		}

		if (cadastre.getM2() != null) {
			this.jLabelM2.setText(cadastre.getM2().toString());
		} else {
			this.jLabelM2.setText("");
		}

		if (cadastre.getM2Covered() != null) {
			this.jLabelM2Covered.setText(cadastre.getM2Covered().toString());
		} else {
			this.jLabelM2Covered.setText("");
		}

		// if (cadastre.getM2CoveredExpanded() != null) {
		// this.jLabelM2CoveredExpanded.setText(cadastre.getM2CoveredExpanded().toString());
		// } else {
		// this.jLabelM2CoveredExpanded.setText("");
		// }

		if (cadastre.getM2CoveredShared() != null) {
			this.jLabelM2CoveredShared.setText(cadastre.getM2CoveredShared().toString());

			if (cadastre.getM2Covered() != null && cadastre.getM2Covered() != 0) {
				this.jLabelM2SharedPercent.setText((cadastre.getM2CoveredShared() / cadastre.getM2Covered()) + "%");
			}

		} else {
			this.jLabelM2CoveredShared.setText("");
			this.jLabelM2SharedPercent.setText("");
		}

	}

	private void printInmAddres() {
		if (cadastre.toStringInmAddres() != null) {
			this.jTextPaneInmAddres.setText(cadastre.toStringInmAddres());
		} else {
			this.jTextPaneInmAddres.setText("");
		}
	}

	private void printUf() {
		if (cadastre instanceof CadastreCensus) {
			CadastreCensus cadastreCensus = (CadastreCensus) cadastre;

			if (cadastreCensus.getUf() != null && cadastreCensus.getUf().getUf() != null) {
				this.jLabelUfId.setText(cadastreCensus.getUf().getUf().getId());
				this.jTextPaneUfDesc.setText(this.cadastre.getUf().toString());
			} else {
				this.jLabelUfId.setText("");
				this.jTextPaneUfDesc.setText("");
			}
		} else {
			if (this.cadastre.getUf() != null) {
				this.jLabelUfId.setText(this.cadastre.getUf().getId());
				this.jTextPaneUfDesc.setText(this.cadastre.getUf().toString());

			} else {
				this.jLabelUfId.setText("");
				this.jTextPaneUfDesc.setText("");
			}
		}
	}

	private void printUser() {
		if (cadastre.toStringUserWaterService() != null) {
			this.jTextPaneUserWaterDesc.setText(cadastre.toStringUserWaterService());
		} else {
			this.jTextPaneUserWaterDesc.setText("");
		}
	}

	private void printClearInmAddres() {
		this.jTextPaneInmAddres.setText("");
	}

	private void printClearUf() {
		this.jLabelUfId.setText("");
		this.jTextPaneUfDesc.setText("");
	}

	private void printClearUser() {
		this.jTextPaneUserWaterDesc.setText("");
	}

	private void printClear() {

		this.jLabelCadastralCode.setText("");

		printClearUf();

		this.jLabelCtaCli.setText("");

		this.jLabelCityArea.setText("");

		this.jLabelSubCtaCli.setText("");

		this.jLabelDv.setText("");

		this.jLabelCadastreType.setText("");

		this.jLabelCadastreSituation.setText("");

		this.jLabelWaterMeter.setText("");

		this.jLabelDateCreate.setText("");

		this.jLabelDateDelete.setText("");

		this.jLabelReasonLow.setText("");

		this.jLabelM2.setText("");

		this.jLabelM2Covered.setText("");

		// this.jLabelM2CoveredExpanded.setText("");

		this.jLabelM2CoveredShared.setText("");

		this.jLabelM2Percent.setText("");

		printClearInmAddres();

		printClearUser();

		this.jLabelAuditVersion.setText("");

		this.jLabelAuditVersionCodeLabel.setText("");

		this.jLabelAuditVersionLabel.setText("");

		this.jLabelAuditDateCreate.setText("");

		this.jLabelAuditUserCreate.setText("");

		this.jLabelAuditDateUpdate.setText("");

		this.jLabelAuditUserUpdate.setText("");

		this.jTextPaneId.setText("");

		this.jLabelCr2.setText("   ");

		this.jLabelCr1.setText("   ");

		this.jLabelCr0.setText("   ");

		this.jLabelDateScanning2.setText("   ");

		this.jLabelDateScanning1.setText("   ");

		this.jLabelDateScanning0.setText("   ");

		this.jLabelCadastreSubDivisionType.setText("");

		this.jLabelCadastreActivityType.setText("");

		this.jLabelM2CoveredPh.setText("Sin calcular");

		this.jLabelCantPh.setText("");

		this.jLabelCantPhCalc.setText("Sin calcular");

		this.jLabelM2CoveredBlock.setText("Sin calcular");

		this.jLabelM2PHPercent.setText("Sin calcular");

		this.jLabelM2SharedPercent.setText("Sin calcular");
	}

	private void find() {

		if (cadastre == null) {
			return;
		}

		if (cadastre instanceof CadastreCensus) {
			CadastreCensus cadastreCensus = (CadastreCensus) cadastre;
			cadastreBlockList = Services.getInstance().findCadastreBlockCensusListByIdCadastre(cadastreCensus.getId());
			if(cadastreCensus.getCadastre() != null){
				cadastreScanningList = Services.getInstance().findScheduleScanningItemListByIdCadastre(cadastreCensus.getCadastre().getId());	
			}			
		} else {
			cadastreBlockList = Services.getInstance().findCadastreBlockListByIdCadastre(cadastre.getId());
			cadastreScanningList = Services.getInstance().findScheduleScanningItemListByIdCadastre(cadastre.getId());
		}

		if (cadastreBlockList != null) {

			double d = 0;

			for (int i = 0; i < cadastreBlockList.length; i++) {
				if (cadastreBlockList[i][4] != null) {
					d += (Double) cadastreBlockList[i][4];
				}
			}

			d = Math.rint(d * 10000) / 10000;

			this.jLabelM2CoveredBlock.setText(d + "");

			double m2Covered = 0;

			if (cadastre.getM2Covered() != null) {
				m2Covered = cadastre.getM2Covered();
			}

			if (d != m2Covered) {
				this.jLabelM2CoveredBlock.setForeground(Color.red);
				jLabelCadastreType.setForeground(Color.red);
			} else if (cadastre.getCadastreType().getId().equals("BA") && d > 0) {
				jLabelCadastreType.setForeground(Color.red);
				this.jLabelM2CoveredBlock.setForeground(Color.red);
			} else {
				this.jLabelM2CoveredBlock.setForeground(new Color(0, 102, 153));
				jLabelCadastreType.setForeground(new Color(0, 102, 153));
			}

			// if (cadastre.getCadastreType().getId().equals("BA") && d > 0) {
			// jLabelCadastreType.setForeground(Color.red);
			// this.jLabelM2CoveredBlock.setForeground(Color.red);
			// } else {
			// this.jLabelM2CoveredBlock.setForeground(new Color(0, 102, 153));
			// jLabelCadastreType.setForeground(new Color(0, 102, 153));
			// }

		} else {
			this.jLabelM2CoveredBlock.setText("0");
			this.jLabelM2CoveredBlock.setForeground(new Color(0, 102, 153));
		}

		if (cadastre.getCadastreType().getId().equals("PV") || cadastre.getCadastreType().getId().equals("PH") || (cadastreBlockList != null && cadastreBlockList.length > 0)) {
			this.jPanelBlock.setVisible(true);
			jLabel100.setVisible(true);
			jLabelM2CoveredBlock.setVisible(true);
		} else {
			this.jPanelBlock.setVisible(false);
			jLabel100.setVisible(false);
			jLabelM2CoveredBlock.setVisible(false);
		}

		jTableBlockList.setModel(new DefaultTableModel(cadastreBlockList, new String[] { "Id", "Año", "Mes", "Tipo", "M2", "Destino", "Actividad" }) {
			Class[] types = new Class[] { String.class, Integer.class, Integer.class, String.class, Double.class, String.class, String.class };
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});

		setWidthColumn(jTableBlockList.getColumnModel().getColumn(0), 0);

		jTableScanningList.setModel(new DefaultTableModel(cadastreScanningList, new String[] { "Id", "Fecha", "Código", "Sup.", "% Av.", "Censista" }) {

			Class[] types = new Class[] { String.class, Date.class, String.class, Double.class, Double.class, String.class };

			boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});

		setWidthColumn(jTableScanningList.getColumnModel().getColumn(0), 0);

		findPhList();

	}

	private void findPhList() {

		this.jLabelCantPhCalc.setForeground(new Color(0, 102, 153));

		if (cadastre instanceof CadastreCensus) {
			CadastreCensus cadastreCensus = (CadastreCensus) cadastre;

			cadastrePhList = Services.getInstance().findCadastreCensusPhListByCadastralCode(cadastreCensus.getId(), cadastreCensus.getScheduleCensus().getId());

		} else {
			cadastrePhList = Services.getInstance().findCadastrePhListByCadastralCode(cadastre.getId(), cadastre.getCadastralCode().substring(0, 10));
		}

		if (cadastre.getCadastreType().getId().equals("PH") == false && cadastrePhList != null && cadastrePhList.length > 0) {
			this.jLabelCadastreType.setForeground(Color.red);
		} else {
			this.jLabelCadastreType.setForeground(Color.black);
		}

		if (cadastre.getCadastreType().getId().equals("PH") && cadastrePhList == null) {
			this.jLabelCantPhCalc.setForeground(Color.red);
		} else {
			this.jLabelCantPhCalc.setForeground(new Color(0, 102, 153));
		}

		if (cadastrePhList != null) {

			double m2Total = 0;
			double m2TotalPercent = 0;

			double m2Covered = 0;
			double m2Percent = 0;

			double m2CoveredShared = 0;
			double m2SharedPercent = 0;

			double sumM2Ph = 0;
			double sumM2PhPercent = 0;

			if (cadastre.getCantPh() != null) {
				this.jLabelCantPh.setText(cadastre.getCantPh() + "");
			}
			this.jLabelCantPhCalc.setText(cadastrePhList.length + "");

			// ------------------------------------------

			if (cadastre.getM2Covered() != null) {
				m2Covered = cadastre.getM2Covered();
			}

			// ------------------------------------------

			m2Percent = 100;
			this.jLabelM2Percent.setText(m2Percent + "%");

			// ------------------------------------------

			if (cadastre.getM2CoveredShared() != null) {
				m2CoveredShared = cadastre.getM2CoveredShared();
			}

			// ------------------------------------------

			for (int i = 0; i < cadastrePhList.length; i++) {
				if (cadastrePhList[i][9] != null) {
					sumM2Ph += (Double) cadastrePhList[i][9];
					sumM2PhPercent += (Double) cadastrePhList[i][10];
				}
			}

			sumM2Ph = Math.rint(sumM2Ph * 10000) / 10000;

			sumM2PhPercent = Math.rint(sumM2PhPercent * 10000) / 10000;

			// System.out.println(Math.rint(25.63874*10000)/10000 + " .... " + Math.rint(25.63875*10000)/10000 + " .... " + Math.rint(25.63876*10000)/10000 + " .... " + Math.rint(sumM2Ph*10000)/10000
			// + " .... " + sumM2Ph );

			this.jLabelM2CoveredPh.setText(sumM2Ph + "");
			this.jLabelM2PHPercent.setText(sumM2PhPercent + "%");

			// ------------------------------------------

			m2SharedPercent = m2CoveredShared / m2Covered;
			this.jLabelM2SharedPercent.setText(m2SharedPercent + "%");

			// ------------------------------------------

			m2Total = sumM2Ph + m2CoveredShared;

			this.jLabelM2CoveredTotal.setText(m2Total + "");

			m2TotalPercent = sumM2PhPercent + m2SharedPercent;

			m2TotalPercent = Math.rint(m2TotalPercent * 10000) / 10000;

			this.jLabelM2PercentTotal.setText(m2TotalPercent + "%");

			// ==============================================================

			if (m2Covered != m2Total) {
				this.jLabelM2CoveredTotal.setForeground(Color.red);
			} else {
				this.jLabelM2CoveredTotal.setForeground(new Color(0, 102, 153));
			}

			// ==============================================================

			if (100.0 != m2TotalPercent) {
				this.jLabelM2CoveredTotal.setForeground(Color.red);
			} else {
				this.jLabelM2CoveredTotal.setForeground(new Color(0, 102, 153));
			}

			// ==============================================================

			if (cadastre.getCadastreType().getId().equals("PH") && cadastrePhList.length == 0) {
				this.jLabelCantPhCalc.setForeground(Color.red);
			} else {
				this.jLabelCantPhCalc.setForeground(new Color(0, 102, 153));
			}

		} else {
			this.jLabelM2CoveredPh.setForeground(new Color(0, 102, 153));
		}

		jTablePhList.setModel(new DefaultTableModel(cadastrePhList, new String[] { "Id", "ppp", "UF", "Cta.Cli.", "Piso", "Dto", "Calle", "N°", "Est.", "M2 Cub.", "%", "Tipo", "Actividad" }) {

			Class[] types = new Class[] { String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Double.class, Double.class,
					String.class, String.class };

			boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});

		if (jTablePhList.getModel().getRowCount() > 0) {

			jTablePhList.getSelectionModel().setSelectionInterval(0, 0);
		}

		setWidthColumn(jTablePhList.getColumnModel().getColumn(0), 0);
		setWidthColumn(jTablePhList.getColumnModel().getColumn(1), 30);
		setWidthColumn(jTablePhList.getColumnModel().getColumn(2), 50);
		setWidthColumn(jTablePhList.getColumnModel().getColumn(3), 70);
		setWidthColumn(jTablePhList.getColumnModel().getColumn(4), 50);
		setWidthColumn(jTablePhList.getColumnModel().getColumn(5), 50);

		setWidthColumn(jTablePhList.getColumnModel().getColumn(7), 40);
		setWidthColumn(jTablePhList.getColumnModel().getColumn(8), 30);
		setWidthColumn(jTablePhList.getColumnModel().getColumn(9), 60);
		setWidthColumn(jTablePhList.getColumnModel().getColumn(10), 40);
		setWidthColumn(jTablePhList.getColumnModel().getColumn(11), 30);
		setWidthColumn(jTablePhList.getColumnModel().getColumn(12), 50);

		if (cadastre.getCadastreType().getId().equals("PH") || (cadastrePhList != null && cadastrePhList.length > 0)) {
			this.jPanelPh.setVisible(true);
			// this.jPanelPhM2.setVisible(true);

			jLabel104.setVisible(true);
			jLabelM2CoveredTotal.setVisible(true);
			jLabelM2PercentTotal.setVisible(true);

			jLabel102.setVisible(true);
			jLabelM2CoveredPh.setVisible(true);
			jLabelM2PHPercent.setVisible(true);

			jLabel119.setVisible(true);
			jLabelM2CoveredShared.setVisible(true);
			jLabelM2SharedPercent.setVisible(true);

			jLabelM2Percent.setVisible(true);

		} else {
			this.jPanelPh.setVisible(false);
			// this.jPanelPhM2.setVisible(false);
			jLabel104.setVisible(false);
			jLabelM2CoveredTotal.setVisible(false);
			jLabelM2PercentTotal.setVisible(false);

			jLabel102.setVisible(false);
			jLabelM2CoveredPh.setVisible(false);
			jLabelM2PHPercent.setVisible(false);

			jLabel119.setVisible(false);
			jLabelM2CoveredShared.setVisible(false);
			jLabelM2SharedPercent.setVisible(false);

			jLabelM2Percent.setVisible(false);

		}

	}

	private void findBlokPhList(String id) {

		if (cadastre instanceof CadastreCensus) {

			cadastrePhBlockList = Services.getInstance().findCadastreBlockCensusListByIdCadastre(id);

		} else {
			cadastrePhBlockList = Services.getInstance().findCadastreBlockListByIdCadastre(id);
		}

		jTableBlockPhList.setModel(new DefaultTableModel(cadastrePhBlockList, new String[] { "Id", "Año", "Mes", "Tipo", "M2", "Destino" }) {
			Class[] types = new Class[] { String.class, Integer.class, Integer.class, String.class, Double.class, String.class };
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});

		setWidthColumn(jTableBlockPhList.getColumnModel().getColumn(0), 0);
	}

	private void setWidthColumn(TableColumn tableColumn, int width) {

		if (width == 0) {
			tableColumn.setMinWidth(0);
			tableColumn.setMaxWidth(0);
			tableColumn.setWidth(0);
			tableColumn.setPreferredWidth(0);
		} else if (width > -1) {
			tableColumn.setWidth(width);
			tableColumn.setPreferredWidth(width);
			tableColumn.setMaxWidth(width);
		}

	}

	private void editInmAddressCustom() {
		new JDialogInmAddressCustom(cadastre, this);
	}

	private void editUfDesc() {
		new JDialogUfCustom(cadastre, this);
	}

	private void editUser() {
		new JDialogUserCustom(cadastre, this);
	}

	private void editGeneral() {
		new JDialogGeneralCustom(cadastre, this);
	}

	private void editCadastreBlock() {
		new JDialogCadastreBlockCustom(cadastre, this);
	}
	
	private void editHeader() {
		new JDialogCensusHeaderFormCustom(cadastre, this);
	}

}
