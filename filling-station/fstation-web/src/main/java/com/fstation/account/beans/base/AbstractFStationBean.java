package com.fstation.account.beans.base;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractFStationBean
{
	
	public AbstractFStationBean()
	{
	}
	
	private static final Logger ABSTRACT_LOGGER = LoggerFactory.getLogger(AbstractFStationBean.class);
	
	private ResourceBundle      messageBundle;
	
	public AbstractFStationBean(final String bundleId)
	{
		try
		{
			messageBundle = ResourceBundle.getBundle(bundleId, FacesContext.getCurrentInstance().getViewRoot().getLocale());
		}
		catch (Exception e)
		{
			ABSTRACT_LOGGER.error("Error retrieving ".concat(bundleId).concat(" bundle!"), e);
		}
	}
	
	public final String findMessageById(final String id)
	{
		try
		{
			return messageBundle.getString(id);
		}
		catch (Exception e)
		{
			ABSTRACT_LOGGER.error("Couldn't get message: ".concat(id).concat(" from messages bundle!"), e);
			return "";
		}
	}
	
	public final ResourceBundle getMessageBundle()
	{
		return messageBundle;
	}
	
	public final void setMessageBundle(final ResourceBundle messageBundle)
	{
		this.messageBundle = messageBundle;
	}
}
