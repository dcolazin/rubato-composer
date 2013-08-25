package org.rubato.rubettes.bigbang.model.edits;

import java.util.List;
import java.util.Map;

import org.rubato.rubettes.bigbang.model.BigBangScoreManager;
import org.rubato.rubettes.util.DenotatorPath;

public class EndWallpaperEdit extends AbstractOperationEdit {
	
	private BigBangScoreManager scoreManager;
	
	public EndWallpaperEdit(BigBangScoreManager manager) {
		super(manager);
		this.scoreManager = manager;
	}
	
	public String getPresentationName() {
		return "End Wallpaper";
	}

	@Override
	public List<Map<DenotatorPath, DenotatorPath>> execute(List<Map<DenotatorPath, DenotatorPath>> pathDifferences, boolean fireCompositionChange) {
		this.scoreManager.endWallpaper(fireCompositionChange);
		return pathDifferences;
	}

	@Override
	public void setInPreviewMode(boolean inPreviewMode) {
		//do nothing for now
	}

}