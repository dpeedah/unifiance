<?php
class Database{
	private $host = "localhost";
	private $username = "root";
	private $password = "";
	private $dbname = "tvuserdb";
	public $conn;

	public function openConnection()
	{
		$this->conn = null;
		try {
			$this->conn = new mysqli($this->host,$this->username,$this->password,$this->dbname) or die("No connection");
			$this->conn->set_charset('utf8mb4');
		} catch (Eception $e)
		{
			echo "connection error";
		}
		

		return $this->conn;
	}
}
?>