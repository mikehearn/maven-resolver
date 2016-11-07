package org.eclipse.aether.util.version;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import static java.util.Objects.requireNonNull;

import org.eclipse.aether.version.Version;
import org.eclipse.aether.version.VersionConstraint;
import org.eclipse.aether.version.VersionRange;

/**
 * A constraint on versions for a dependency.
 */
final class GenericVersionConstraint
    implements VersionConstraint
{

    private final VersionRange range;

    private final Version version;

    /**
     * Creates a version constraint from the specified version range.
     *
     * @param range The version range, must not be {@code null}.
     */
    public GenericVersionConstraint( VersionRange range )
    {
        this.range = requireNonNull( range, "version range cannot be null" );
        this.version = null;
    }

    /**
     * Creates a version constraint from the specified version.
     *
     * @param version The version, must not be {@code null}.
     */
    public GenericVersionConstraint( Version version )
    {
        this.version = requireNonNull( version, "version cannot be null" );
        this.range = null;
    }

    public VersionRange getRange()
    {
        return range;
    }

    public Version getVersion()
    {
        return version;
    }

    public boolean containsVersion( Version version )
    {
        if ( range == null )
        {
            return version.equals( this.version );
        }
        else
        {
            return range.containsVersion( version );
        }
    }

    @Override
    public String toString()
    {
        return String.valueOf( ( range == null ) ? version : range );
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null || !getClass().equals( obj.getClass() ) )
        {
            return false;
        }

        GenericVersionConstraint that = (GenericVersionConstraint) obj;

        return eq( range, that.range ) && eq( version, that.getVersion() );
    }

    private static <T> boolean eq( T s1, T s2 )
    {
        return s1 != null ? s1.equals( s2 ) : s2 == null;
    }

    @Override
    public int hashCode()
    {
        int hash = 17;
        hash = hash * 31 + hash( getRange() );
        hash = hash * 31 + hash( getVersion() );
        return hash;
    }

    private static int hash( Object obj )
    {
        return obj != null ? obj.hashCode() : 0;
    }

}
