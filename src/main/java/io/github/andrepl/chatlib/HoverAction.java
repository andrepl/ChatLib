package io.github.andrepl.chatlib;

import net.minecraft.server.v1_7_R3.EnumHoverAction;

public enum HoverAction {
	SHOW_TEXT(EnumHoverAction.SHOW_TEXT),
	SHOW_ITEM(EnumHoverAction.SHOW_ITEM),
	SHOW_ACHIEVEMENT(EnumHoverAction.SHOW_ACHIEVEMENT);

	private EnumHoverAction hoverAction;

	HoverAction(EnumHoverAction hoverAction) {
		this.hoverAction = hoverAction;
	}

	public EnumHoverAction getNMS() {
		return hoverAction;
	}
}