 /***************************************************************************\*
 *                                                                            *
 *    AntForm form-based interaction for Ant scripts                          *
 *    Copyright (C) 2005 Ren� Ghosh                                           *
 *                                                                            *
 *   This library is free software; you can redistribute it and/or modify it  *
 *   under the terms of the GNU Lesser General Public License as published by *
 *   the Free Software Foundation; either version 2.1 of the License, or (at  *
 *   your option) any later version.                                          *
 *                                                                            *
 *   This library is distributed in the hope that it will be useful, but      *
 *   WITHOUT ANY WARRANTY; without even the implied warranty of               *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser  *
 *   General Public License for more details.                                 *
 *                                                                            *
 *   You should have received a copy of the GNU Lesser General Public License *
 *   along with this library; if not, write to the Free Software Foundation,  *
 *   Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA              *
 \****************************************************************************/
package com.sardak.antform.test;

import java.util.Properties;

import javax.swing.UIManager;

import com.sardak.antform.gui.ButtonPanel;
import com.sardak.antform.gui.Control;
import com.sardak.antform.gui.ControlPanel;
import com.sardak.antform.interfaces.ValueHandle;
import com.sardak.antform.types.BooleanProperty;
import com.sardak.antform.types.Link;
import com.sardak.antform.types.LinkBar;
import com.sardak.antform.types.Separator;
import com.sardak.antform.types.TextProperty;

/**
 * @author Ren� Ghosh
 * 20 mars 2005
 */
public class ControlsTest {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Control control = new Control(new CallbackTest(), "Configure FTP Servers", null, null, false);
		ControlPanel panel = control.getPanel();
		
		LinkBar bar = new LinkBar();
		bar.addConfiguredLink(new Link("Add an FTP server", "addserver"));
		bar.addConfiguredLink(new Link("Remove an FTP server", "removeServer"));
		bar.addToControlPanel(panel);
		Separator sep = new Separator();
		sep.addToControlPanel(panel);
		
		BooleanProperty bp = new BooleanProperty();
		bp.setLabel("Passive-mode connection:");
		bp.setProperty("pasv");
		bp.setEditable(true);
//		ValueHandle g1 = panel.addBooleanProperty("Passive-mode connection:", "pasv", true, null);
		ValueHandle g1 = bp.addToControlPanel(panel);
		TextProperty tp1 = new TextProperty();
		tp1.setLabel("Server address:");
		tp1.setProperty("serverAddress");
		tp1.setColumns(30);
		tp1.setEditable(true);
		tp1.setPassword(false);
		tp1.setRequired(false);
		ValueHandle g2 = tp1.addToControlPanel(panel);
//		ValueHandle g2 = panel.addTextProperty("Server address:", "serverAddress", 30, true, false, false, null);
		TextProperty tp2 = new TextProperty();
		tp2.setLabel("Server login:");
		tp2.setProperty("login");
		tp2.setColumns(30);
		tp2.setEditable(true);
		tp2.setPassword(false);
		tp2.setRequired(false);
		tp2.addToControlPanel(panel);
		TextProperty tp3 = new TextProperty();
		tp3.setLabel("Server password:");
		tp3.setProperty("password");
		tp3.setColumns(30);
		tp3.setEditable(true);
		tp3.setPassword(true);
		tp3.setRequired(false);
		tp3.addToControlPanel(panel);
		
		control.getPanel().addButtonPanel(new ButtonPanel("Save properties", "Reset form", null, control.getPanel()));
		
		Properties props = new Properties();
		props.setProperty("pasv", "true");
		control.initProperties(props);
		g2.setValue("login");
		control.show();
		
		
		panel.getProperties().list(System.out);
		System.out.println(g1.getValue());
		System.exit(0);
	}
}
