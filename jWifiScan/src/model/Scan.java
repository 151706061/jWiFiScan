package model;

public class Scan {
	// "00:22:43:1E:FC:59","11","2.462","70/70","-39","on","VANCL"
	private String mac;
	private String channel;
	private String freq;
	private String quality;
	private String signal;
	private String enc;
	private String ssid;
	
	public Scan() {
	}
	
	public Scan(String mac, String channel, String freq, String quality, String signal, String enc, String ssid) {
		this.mac = mac;
		this.channel = channel;
		this.freq = freq;
		this.quality = quality;
		this.signal = signal;
		this.enc = enc;
		this.ssid = ssid;
	}
	
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getFreq() {
		return freq;
	}
	public void setFreq(String freq) {
		this.freq = freq;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getSignal() {
		return signal;
	}
	public void setSignal(String signal) {
		this.signal = signal;
	}
	public String getEnc() {
		return enc;
	}
	public void setEnc(String enc) {
		this.enc = enc;
	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	@Override
	public String toString() {
		return "Scan [mac=" + mac + ", channel=" + channel + ", freq=" + freq
				+ ", quality=" + quality + ", signal=" + signal + ", enc="
				+ enc + ", ssid=" + ssid + "]";
	}
	
}
