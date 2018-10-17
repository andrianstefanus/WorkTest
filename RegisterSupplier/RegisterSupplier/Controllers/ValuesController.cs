using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using RegisterSupplier.Class;
using RegisterSupplier.Models;

namespace RegisterSupplier.Controllers
{
    public class ValuesController : ApiController
    {
        // GET api/values
        public IEnumerable<string> Get()
        {
            return new string[] { "value1", "value2" };
        }

        // GET api/values/5
        public string Get(int id)
        {
            return "value" + id;
        }
        
        [HttpPost]
        [Route("Register/Testing")]
        public MasterClass.Hasil PostTest(MasterClass.Register input)
        {
            MasterModel Register = new MasterModel();
            return Register.Register(input);
        }

        // POST api/values
        public void Post([FromBody]string value)
        {
        }

        // PUT api/values/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE api/values/5
        public void Delete(int id)
        {
        }
    }
}
