package org.tryndusi.discovery;

public class Service implements Discoverable {

    private String discoveryPath;
    private String hostname;
    private int port;

    public Service() {
    }

    public static Service create(String discoveryPath, String hostname, int port) {
        final Service ts = new Service();
        ts.discoveryPath = discoveryPath;
        ts.hostname = hostname;
        ts.port = port;
        return ts;
    }

    @Override
    public String getDiscoveryPath() {
        return discoveryPath;
    }

    @Override
    public String getHostname() {
        return hostname;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((discoveryPath == null) ? 0 : discoveryPath.hashCode());
        result = prime * result + ((hostname == null) ? 0 : hostname.hashCode());
        result = prime * result + port;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Service other = (Service) obj;
        if (discoveryPath == null) {
            if (other.discoveryPath != null)
                return false;
        } else if (!discoveryPath.equals(other.discoveryPath))
            return false;
        if (hostname == null) {
            if (other.hostname != null)
                return false;
        } else if (!hostname.equals(other.hostname))
            return false;
        if (port != other.port)
            return false;
        return true;
    }
}
