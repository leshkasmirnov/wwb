/**
 * 
 */
package com.wildwestbank.modules.app.internal;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public enum TransactionTypes {

	UP("Пополнение", 1),

	DOWN("Списание", 2),

	TRANSFER("Перевод", 3);

	private final String description;
	private final int value;

	private TransactionTypes(String description, int value) {
		this.description = description;
		this.value = value;
	}

	// @Override
	// public String toString() {
	// return description;
	// }

	public String getDescription() {
		return description;
	}

	public int getValue() {
		return value;
	}

}
