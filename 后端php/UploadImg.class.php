<?php
class UploadImg{
    private $dir;
    private $file;

    /**
     * Upload constructor.
     * @param $dir  上传的目录路径
     * @param $file 上传的文件
     */

    function __construct($dir,$file)
    {
       $this->file = $file;
        $this->dir = $dir;
       $this->upload_img();
    }

    private function upload_img()
    {
        if (!$this->file_type()) {
            echo "只支持jpg jpeg png gif图片格式";
        } else if (!$this->file_size()) {
            echo "图片不能大于2M";
        } else if ($this->file_type() && $this->file_size()) {
            //判断是否上传成功
            if (is_uploaded_file($this->file["tmp_name"])) {
                $fileName = time() . rand(10000, 99999) . substr($this->file["name"], strrpos($this->file["name"], "."));
                $uploadPath = $this->dir . "/" . $fileName;
                //把文件转存到指定目录
                if (move_uploaded_file($this->file["tmp_name"], $uploadPath)) {
                    echo $fileName;
                    //上传成功
                } else {
                    echo "上传失败,请重试";
                }
            }
        }
    }

    private function file_type()
    {
        $type= substr($this->file["name"], strrpos($this->file["name"], "."));
        $type=substr($type,1);
        if ($type == "gif"
            || $type== "jpg"
            || $type== "png"
            || $type== "jpeg"
        ) {
            return true;
        } else {
            return false;
        }
    }

    private function file_size()
    {
        if (($this->file["size"] < 2 * 1024 * 1024)) {
            return true;
        } else {
            return false;
        }
    }
}

?>