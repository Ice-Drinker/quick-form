/**
 * 
 */
package com.ice.service;

/**
 * @author ice-drinker
 * @date 2014-3-17
 */
public interface IiceUI {

	/**
	 * @return
	 */
	public String getValue();

	/**
	 * @param value
	 */
	public void setValue(String value);

	/**
	 * @return
	 */
	public String getKey();

	/**
	 * whether the value changes
	 * 
	 * @return
	 */
	public boolean isPollute();
}
