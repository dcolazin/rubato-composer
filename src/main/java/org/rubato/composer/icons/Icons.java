/*
 * Copyright (C) 2005 Gérard Milmeister
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of version 2 of the GNU General Public
 * License as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 */

package org.rubato.composer.icons;

import java.net.URL;

import javax.swing.ImageIcon;

@SuppressWarnings("nls")
public class Icons {
    
    public final static ImageIcon emptyIcon; 
    public final static ImageIcon newIcon;
    public final static ImageIcon openIcon;
    public final static ImageIcon revertIcon;
    public final static ImageIcon addIcon;
    public final static ImageIcon saveIcon;
    public final static ImageIcon saveasIcon;
    public final static ImageIcon quitIcon;
    public final static ImageIcon newnetIcon;
    public final static ImageIcon closeIcon;
    public final static ImageIcon runIcon;
    public final static ImageIcon runContIcon;
    public final static ImageIcon stopIcon;
    public final static ImageIcon prefIcon;
    public final static ImageIcon rubatoIcon; 
    public final static ImageIcon splashIcon; 
    public final static ImageIcon denoIcon; 
    public final static ImageIcon formIcon; 
    public final static ImageIcon moduleIcon; 
    public final static ImageIcon morphIcon; 
    public final static ImageIcon limitIcon; 
    public final static ImageIcon colimitIcon; 
    public final static ImageIcon powerIcon; 
    public final static ImageIcon listIcon; 
    public final static ImageIcon simpleIcon; 
    public final static ImageIcon midiinIcon; 
    public final static ImageIcon midioutIcon; 
    public final static ImageIcon tdownIcon; 
    public final static ImageIcon trightIcon; 
    public final static ImageIcon schemeIcon; 
    public final static ImageIcon schemeEditIcon; 
    public final static ImageIcon browseIcon;
    public final static ImageIcon cutIcon;
    public final static ImageIcon copyIcon;
    public final static ImageIcon pasteIcon;

    
    public static ImageIcon loadIcon(Object obj, String name) {
        URL imageURL;
        imageURL = obj.getClass().getResource(name);
        return new ImageIcon(imageURL);
    }
    
    
    public static ImageIcon loadIcon(Class<?> cls, String name) {
        URL imageURL;
        imageURL = cls.getResource(name);
        return new ImageIcon(imageURL);
    }

    
    public static ImageIcon loadIcon(String name) {
        return loadIcon(Icons.class, name);
    }
    

    static {
        emptyIcon   = loadIcon("/images/composer/icons/emptyicon.png");
        newIcon     = loadIcon("/images/composer/icons/newicon.png");
        openIcon    = loadIcon("/images/composer/icons/openicon.png");
        revertIcon  = loadIcon("/images/composer/icons/reverticon.png");
        addIcon     = loadIcon("/images/composer/icons/addicon.png");
        saveIcon    = loadIcon("/images/composer/icons/saveicon.png");
        saveasIcon  = loadIcon("/images/composer/icons/saveasicon.png");
        quitIcon    = loadIcon("/images/composer/icons/quiticon.png");
        runIcon     = loadIcon("/images/composer/icons/runicon.png");
        runContIcon = loadIcon("/images/composer/icons/runconticon.png");
        stopIcon    = loadIcon("/images/composer/icons/stopicon.png");
        newnetIcon  = loadIcon("/images/composer/icons/newneticon.png");
        closeIcon   = loadIcon("/images/composer/icons/closeicon.png");
        prefIcon    = loadIcon("/images/composer/icons/preficon.png");
        rubatoIcon  = loadIcon("/images/composer/icons/rubatoicon.png");
        splashIcon  = loadIcon("/images/composer/icons/splash.png");
        denoIcon    = loadIcon("/images/composer/icons/denotator.png");
        formIcon    = loadIcon("/images/composer/icons/form.png");
        moduleIcon  = loadIcon("/images/composer/icons/module.png");
        morphIcon   = loadIcon("/images/composer/icons/morph.png");
        limitIcon   = loadIcon("/images/composer/icons/limit.png");
        colimitIcon = loadIcon("/images/composer/icons/colimit.png");
        powerIcon   = loadIcon("/images/composer/icons/power.png");
        listIcon    = loadIcon("/images/composer/icons/list.png");
        simpleIcon  = loadIcon("/images/composer/icons/simple.png");
        midiinIcon  = loadIcon("/images/composer/icons/midiin.png");
        midioutIcon = loadIcon("/images/composer/icons/midiout.png");
        tdownIcon   = loadIcon("/images/composer/icons/triandown.png");
        trightIcon  = loadIcon("/images/composer/icons/trianright.png");
        schemeIcon  = loadIcon("/images/composer/icons/schemeicon.png");
        schemeEditIcon  = loadIcon("/images/composer/icons/schemeediticon.png");
        browseIcon  = loadIcon("/images/composer/icons/browseicon.png");
        cutIcon     = loadIcon("/images/composer/icons/cuticon.png");
        copyIcon    = loadIcon("/images/composer/icons/copyicon.png");
        pasteIcon   = loadIcon("/images/composer/icons/pasteicon.png");
    }
}
