using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using RegisterSupplier.Class;

namespace RegisterSupplier.Models
{

    public class MasterModel
    {
        public MasterClass.Hasil Register (MasterClass.Register input)
        {
            //MasterClass.Register Master = new MasterClass.Register
            //{
            //    Nama = "Andrian"
            //};
            MasterClass.Hasil hasil = new MasterClass.Hasil
            {
                Result = input.Nama
            };
            return hasil;
        }

        
    }

}