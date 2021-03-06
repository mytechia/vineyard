/*******************************************************************************
 *
 * Copyright (C) 2015 Mytech Ingenieria Aplicada <http://www.mytechia.com>
 * Copyright (C) 2015 Alejandro Paz <alejandropl@lagostelle.com>
 *
 * This file is part of Vineyard.
 *
 * Vineyard is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * Vineyard is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Vineyard. If not, see <http://www.gnu.org/licenses/>.
 *
 ******************************************************************************/

package com.hi3project.vineyard.comm.jms.channel;

import com.hi3project.broccoli.bsdl.api.registry.IBSDLRegistry;
import com.hi3project.broccoli.bsdl.impl.exceptions.ModelException;
import com.hi3project.broccoli.bsdm.api.serializing.IMessageSerializer;
import com.hi3project.broccoli.bsdm.impl.exceptions.ServiceGroundingException;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Topic;

/**
 *
 * <p><b>Changelog:</b></p>
 * <ul>
 * <li> 1 , 23-06-2014 - Initial release</li>
 * </ul>
 *
 * 
 * @version 1
 */
public class ControlJMSChannelProducer extends AbstractJMSChannelProducer
{        
    
    
    public ControlJMSChannelProducer(
            IMessageSerializer messageSerializer,
            String connection,
            String name) throws ModelException
    {
        super(messageSerializer, connection, name);
    }
    
    public ControlJMSChannelProducer(
            IMessageSerializer messageSerializer,
            String connection, 
            String name, 
            IBSDLRegistry bsdlRegistry) throws ServiceGroundingException
    {
        super(messageSerializer, connection, name, bsdlRegistry);
    }
    
 
    @Override
    protected MessageProducer initializeMessageProducer(String name) throws ServiceGroundingException
    {
        try
        {
            Topic topic = this.session.createTopic(name);
            this.messageProducer = this.session.createProducer(topic);
            return this.messageProducer;
        } catch (JMSException ex)
        {
            throw new ServiceGroundingException("Cannot create control topic for: " + name, ex);
        }
    }
    

    

}
